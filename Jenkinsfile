pipeline {
  agent {
    docker {
      image 'diffblue/internal-cover-cli:release-2024.08.02-2024.08.02-rc1-jdk17'
    }    
  }

  environment {
    DIFFBLUE_LICENSE_KEY = credentials('diffblue-license')
    //DIFFBLUE_HEAD_BRANCH = $GIT_BRANCH
    //DIFFBLUE_BASE_BRANCH = 'main'
    //DIFFBLUE_REPOSITORY_URL = https://github.com/TGWDB/demo-spring-petclinic-Jenkins.git
  }

  stages {
    stage('Write tests') {
      steps {
        sh '''
          git branch
          git fetch --all
          git checkout main
          env
          DIFFBLUE_HEAD_BRANCH=${GIT_BRANCH} dcover ci activate build create
        '''
      }
    }
  }
}
