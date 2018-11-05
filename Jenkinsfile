pipeline {
  agent any
  stages {
    stage('DEVCI') {
      parallel {
        stage('DEVCI') {
          steps {
            echo 'dev ci step'
            sh 'echo hello'
            build(job: 'sampletest', propagate: true)
          }
        }
        stage('different branch') {
          steps {
            git(url: 'git@github.com:taowusalesforce/Tao-TestNG.git', branch: 'test')
            build 'sampletest'
          }
        }
      }
    }
  }
}