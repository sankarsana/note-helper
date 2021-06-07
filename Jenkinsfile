#!groovy
pipeline {
    agent {
        docker {
            image 'jangrewe/gitlab-ci-android'
            args '-it --memory=26g --cpus="3"'
        }
    }

    stages {
        stage('prepare') {
            steps {
                sh "chmod +x gradlew"
            }
        }
        stage('build') {
            steps {
                sh "./gradlew clean assembleDebug --no-daemon"
            }
        }
        stage('tests') {
            steps {
                sh "./gradlew test"
            }
        }
    }
}
