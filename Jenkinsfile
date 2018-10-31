pipeline {
  agent any
  stages {
    stage('DEVCI') {
      steps {
        echo 'dev ci step'
        sh 'echo hello'
      }
    }
    stage('QA') {
      steps {
        echo 'QA step'
        sh 'echo Hello QA'
      }
    }
  }
}