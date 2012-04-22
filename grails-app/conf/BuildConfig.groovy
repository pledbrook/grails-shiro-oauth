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
    }

    plugins {
        compile ":shiro:1.1.3", ":oauth-scribe:1.3"
    }
}
