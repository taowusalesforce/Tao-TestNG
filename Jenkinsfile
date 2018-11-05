pipeline {
  agent any
  stages {
    stage('DEVCI') {
      parallel {
        stage('DEVCI') {
          steps {
            echo 'dev ci step'
            git(url: 'git@github.com:taowusalesforce/Tao-TestNG.git', branch: 'test')
            sh 'sh ./show_repo.sh'
          }
        }
        stage('different branch') {
          steps {
            git(url: 'git@github.com:taowusalesforce/Test-GraphQL.git', branch: 'pipeline')
            sh 'sh ./show_repo.sh'
          }
        }
      }
    }
  }
}