package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.management.loading.MLet;
import org.apache.catalina.webresources.ClasspathURLStreamHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.aot.hint.JavaSerializationHint;
import org.springframework.aot.hint.RuntimeHints;

class PetClinicRuntimeHintsDiffblueTest {
  /**
   * Method under test:
   * {@link PetClinicRuntimeHints#registerHints(RuntimeHints, ClassLoader)}
   */
  @Test
  void testRegisterHints() {
    // Arrange
    PetClinicRuntimeHints petClinicRuntimeHints = new PetClinicRuntimeHints();
    RuntimeHints hints = new RuntimeHints();

    // Act
    petClinicRuntimeHints.registerHints(hints, new MLet());

    // Assert
    Stream<JavaSerializationHint> javaSerializationHintsResult = hints.serialization().javaSerializationHints();
    List<JavaSerializationHint> collectResult = javaSerializationHintsResult.limit(5).collect(Collectors.toList());
    assertEquals(3, collectResult.size());
    assertNull(collectResult.get(0).getReachableType());
    assertNull(collectResult.get(1).getReachableType());
    assertNull(collectResult.get(2).getReachableType());
  }

  /**
   * Method under test:
   * {@link PetClinicRuntimeHints#registerHints(RuntimeHints, ClassLoader)}
   */
  @Test
  void testRegisterHints2() throws MalformedURLException {
    // Arrange
    PetClinicRuntimeHints petClinicRuntimeHints = new PetClinicRuntimeHints();
    RuntimeHints hints = new RuntimeHints();
    URLStreamHandlerFactory urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
    when(urlStreamHandlerFactory.createURLStreamHandler(Mockito.<String>any()))
        .thenReturn(new ClasspathURLStreamHandler());

    // Act
    petClinicRuntimeHints.registerHints(hints,
        new URLClassLoader(new URL[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()},
            new MLet(), urlStreamHandlerFactory));

    // Assert
    verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
    Stream<JavaSerializationHint> javaSerializationHintsResult = hints.serialization().javaSerializationHints();
    List<JavaSerializationHint> collectResult = javaSerializationHintsResult.limit(5).collect(Collectors.toList());
    assertEquals(3, collectResult.size());
    assertNull(collectResult.get(0).getReachableType());
    assertNull(collectResult.get(1).getReachableType());
    assertNull(collectResult.get(2).getReachableType());
  }
}
