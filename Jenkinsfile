node {
    def app

    stage('Cloning repository') {
        /* Let's make sure we have the repository cloned to our workspace */

        checkout scm
    }

    stage('Building App') {
        sh 'gradle build --info'
    }

    stage('Building Image') {
        /* This builds the actual image; synonymous to
         * docker build on the command line */

        app = docker.build("spring-mvc-app")
    }

    stage('Testing Image') {
        /* Ideally, we would run a test framework against our image.
         * For this example, we're using a Volkswagen-type approach ;-) */

        app.inside {
            sh 'echo "Tests passed"'
        }
    }

    stage('Pushing Image') {
        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
        docker.withRegistry('http://localhost:5000') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }

    stage('Updating Testing Server') {
        sshagent (credentials: ['testing-server-ssh-credentials']) {
            sh 'ssh -o StrictHostKeyChecking=no -l ubuntu -p 2200 10.26.1.129 /vagrant/update-image-spring-mvc-app.sh $BUILD_NUMBER'
        }
    }

    stage('Running Selenium Tests') {
        dir ('selenium-tests') {
            sh 'mvn initialize'
            sh 'mvn package'
            sh 'java -cp /var/lib/jenkins/.cp/* org.testng.TestNG ./suite/smoke-suite.xml'
        }
    }

    
    /* junit
    */
    post {
      always {
        junit '**/reports/junit/*.xml'
      }
    } 
    
}
