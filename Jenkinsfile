pipeline {
  agent any
  stages {
    stage('DEVCI') {
      parallel {
        stage('Tao-TestNG test branch') {
          steps {
            echo 'dev ci step'
            git(url: 'git@github.com:taowusalesforce/Tao-TestNG.git', branch: 'test', poll: true)
            sh 'sh ./show_repo.sh'
            sh '''BASEFOLDER=`basename "$WORKSPACE"`
echo $BASEFOLDER
export env.REPO_ROOT=/var/lib/jenkins/workspace/$BASEFOLDER

echo $REPO_ROOT
'''
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
    stage('Email') {
      steps {
        emailext(subject: 'pipeline done', body: 'pipeline done', to: 'tao.wu@salesforce.com')
        sh 'echo $REPO_ROOT'
      }
    }
  }
}