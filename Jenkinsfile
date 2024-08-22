pipeline {
  agent any

  environment {
    DIFFBLUE_LICENSE_KEY = credentials('diffblue-license')
    DIFFBLUE_COVER_URL = 'https://release.diffblue.com/cli/latest'
    CI = true
  }

  stages {
    stage('Use dcover create in Jenkins') {
        steps {
            sh '''
                echo "Get and unzip dcover jars into directory dcover, store dcover script location for later use"
                mkdir --parents dcover
                curl --silent --show-error --location --output "dcover/dcover.zip" "$DIFFBLUE_COVER_URL"
                unzip -o dcover/dcover.zip -d dcover
                DCOVER_SCRIPT_LOCATION="dcover/dcover"
                echo "The moment of truth"
                "$DCOVER_SCRIPT_LOCATION" ci activate build create
            '''
        }
    }
  }
}
