pipeline {
  agent any
  stages {
    stage('DEVCI') {
      parallel {
        stage('Tao-TestNG test branch') {
          steps {
            echo 'dev ci step'
            git(url: 'git@github.com:taowusalesforce/Tao-TestNG.git', branch: 'test')
            sh 'sh ./show_repo.sh'
          }
        }
        stage('Test-GraphQL pipeline branch') {
          steps {
            git(url: 'git@github.com:taowusalesforce/Test-GraphQL.git', branch: 'pipeline')
            sh 'sh ./show_repo.sh'
          }
        }
      }
    }
    stage('') {
      steps {
        emailext(subject: 'pipeline done', body: 'pipeline done', to: 'tao.wu@salesforce.com')
      }
    }
  }
}