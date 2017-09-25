node {
    def app
    
    try {
        notifyBuild('STARTED')

        stage('Cloning repository') {
            checkout scm
        }
        
        stage('Testing App (JUnit)') {
            sh 'gradle cleanTest test --info'
            junit '**/build/test-results/*.xml'
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
                sh 'uname -a'
                sh 'pwd'
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
                sh 'ssh -o StrictHostKeyChecking=no -l ubuntu -p 2202 192.168.0.6 /vagrant/update-image-spring-mvc-app.sh $BUILD_NUMBER'
            }
        }
    
    } catch (e) {
        // If there was an exception thrown, the build failed
        currentBuild.result = "FAILED"
        throw e
    } finally {
        notifyBuild(currentBuild.result)
    }
}

def notifyBuild(String buildStatus = 'STARTED') {
    // build status of null means successful
    buildStatus =  buildStatus ?: 'SUCCESSFUL'

    // Default values
    def colorName = 'RED'
    def colorCode = '#FF0000'
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def summary = "${subject} (${env.BUILD_URL})"
    def details = """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>"""

    // Override default values based on build status
    if (buildStatus == 'STARTED') {
        color = 'YELLOW'
        colorCode = '#FFFF00'
    } else if (buildStatus == 'SUCCESSFUL') {
        color = 'GREEN'
        colorCode = '#00FF00'
    } else {
        color = 'RED'
        colorCode = '#FF0000'
    }

    // Send notifications
    emailext (
        subject: subject,
        body: details,
        to: 'jorge.alexandro@gmail.com',
        recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
}