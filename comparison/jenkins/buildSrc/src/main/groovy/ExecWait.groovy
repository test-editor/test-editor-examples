import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Initial version taken from: https://fbflex.wordpress.com/2013/03/14/gradle-madness-execwait-a-task-that-waits-for-commandline-calls-to-be-ready/
 * But improved by proper termination.
 */
class ExecWait extends DefaultTask {

    String command
    String ready
    String directory

    Process process

    @TaskAction
    def spawnProcess() {

        ProcessBuilder builder = new ProcessBuilder(command.split(' '))
        builder.redirectErrorStream(true)
        builder.directory(new File(directory))
        process = builder.start()

        InputStream stdout = process.getInputStream()
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout))

        def line
        while ((line = reader.readLine()) != null) {
            println line
            if (line.contains(ready)) {
                println "$command is ready"
                break;
            }
        }
    }

    def void stopProcess() {
        if (process != null) {
            process.destroy()
            println "Destroyed process for $command"
        }
    }

}
