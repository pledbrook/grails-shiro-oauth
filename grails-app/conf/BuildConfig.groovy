grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6

grails.project.dependency.resolution = {
    inherits "global"
    log "warn"
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        compile "org.apache.shiro:shiro-core:1.1.0"
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0", {
            export = false
        }
    }

    plugins {
        build ":release:2.2.0", {
            export = false
        }

        compile ":shiro:1.1.3", ":oauth:2.0.1"
        test ":spock:0.7", {
            exclude "spock-grails-support"
            export = false
        }
    }
}
