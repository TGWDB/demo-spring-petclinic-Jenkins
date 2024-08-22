pipeline {
  agent {
    docker {
      image 'diffblue/internal-cover-cli:release-2024.08.02-2024.08.02-rc1-jdk17'
    }    
  }

  environment {
    DIFFBLUE_LICENSE_KEY = credentials('diffblue-license')
  }

  stages {
    stage('Write tests') {
      steps {
        sh '''
          dcover ci activate build create
        '''
      }
    }
    stage('Commit tests to branch') {
      steps {
        sh '''#!/bin/bash
          git config user.name Diffblue
          git config user.email diffblue@example.org

          if [ -n "$(git status --short **/*DiffblueTest.java)" ]; then 
            git add **/*DiffblueTest.java
            git commit --message "Update Unit Tests for $(git rev-parse --short HEAD)"
            git push --set-upstream origin
          else
            echo "Nothing to commit"
          fi
        '''
      }
    }
  }
}
