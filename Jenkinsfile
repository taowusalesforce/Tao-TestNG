pipeline {
  agent any
  stages {
    stage('DEVCI') {
      steps {
        echo 'dev ci step'
        sh 'echo hello'
        build(job: 'sampletest', propagate: true)
      }
    }
  }
}