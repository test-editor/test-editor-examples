nodeWithProperWorkspace {
    stage 'Checkout'
    checkout scm
    sh "git clean -ffdx"

    stage 'swing-demo'
    testWithGradle('swing-demo')
    testWithMaven('swing-demo')
    
    stage 'comparison-jenkins'
    testWithGradle('comparison/jenkins')
}

/**
 * Runs ./gradlew clean build in the specified folder.
 */
void testWithGradle(String folder) {
    withXvfb {
        withMavenEnv {
            sh "$folder/gradlew clean build -p $folder"
        }
    }
}

/**
 * Runs mvn clean verify in the specified folder.
 */
void testWithMaven(String folder) {
    withXvfb {
        withMavenEnv {
            def pwd = pwd()
            sh "cd $folder && mvn clean verify -B -V && cd $pwd"
        }
    }
}

// TODO stuff below is copied from test-editor-xtext - we need to extract this common logic

void withXvfb(def body) {
    // TODO why do we have more than one installation on our Jenkins? If we had one we wouldn't need to specify the installationName
    wrap([$class: 'Xvfb', installationName: 'System', timeout: 2, screen: '1024x768x24', displayNameOffset: 1, autoDisplayName: true], body)
}

/** Calls Maven with the given argument and adds the -B (batch) and -V (version) flag. */
void mvn(String argument) {
    sh "mvn $argument -B -V"
}

void withMavenEnv(List envVars = [], def body) {
    String jdkTool = tool name: 'jdk8', type: 'hudson.model.JDK'
    String mvnTool = tool name: 'Maven 3.2.5', type: 'hudson.tasks.Maven$MavenInstallation'
    List mvnEnv = [
        "PATH+JDK=${jdkTool}/bin",
        "JAVA_HOME=${jdkTool}",
        "PATH+MVN=${mvnTool}/bin",
        "MAVEN_HOME=${mvnTool}"
    ]
    mvnEnv.addAll(envVars)
    withEnv(mvnEnv) {
        body.call()
    }
}

/**
 * Workaround for Jenkins bug with feature branches (workspace has feature%2Fmy_feature in it).
 * See https://issues.jenkins-ci.org/browse/JENKINS-30744 (marked as resolved but still occurs).
 */
void nodeWithProperWorkspace(def body) {
    node {
        ws(getWorkspace()) {
            body.call()
        }
    }
}

def getWorkspace() {
    pwd().replace("%2F", "_")
}
