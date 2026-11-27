pipeline {
  agent any

  tools {
    maven 'M2_HOME'
    jdk 'JAVA_HOME'
  }

  environment {
    APP_DIR    = 'backend'
    IMAGE_NAME = 'makramharb1996/devops'
    IMAGE_TAG  = "${BUILD_NUMBER}"
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/Makram-harb/devops.git'
      }
    }

    stage('Build') {
      steps {
        dir("${APP_DIR}") {
          sh 'mvn clean package -DskipTests'
        }
      }
    }

    stage('Tests') {
      steps {
        dir("${APP_DIR}") {
          sh 'mvn test'
        }
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: "${APP_DIR}/target/surefire-reports/*.xml"
        }
      }
    }

    stage('Analyse SonarQube') {
  steps {
    dir("${APP_DIR}") {
      withSonarQubeEnv('SonarQube') {
        sh '''
          mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
            -Dsonar.projectKey=Devops-Project \
            -Dsonar.projectName=DevopsProject \
            -Dsonar.host.url=$SONAR_HOST_URL \
            -Dsonar.token=$SONAR_AUTH_TOKEN
        '''
      }
    }
  }
}

    stage('Docker Build') {
      steps {
        dir("${APP_DIR}") {
          sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG -t $IMAGE_NAME:latest .'
        }
      }
    }

    stage('Docker Push') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                                          usernameVariable: 'DH_USER',
                                          passwordVariable: 'DH_PASS')]) {
          sh '''
            echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin
            docker push $IMAGE_NAME:$IMAGE_TAG
            docker push $IMAGE_NAME:latest
          '''
        }
      }
    }
  }

  post {
    always {
      sh 'docker logout || true'
    }
  }
}
