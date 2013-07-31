//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//    ant.mkdir(dir:"${basedir}/grails-app/jobs")
//
println """\
WARNING

Version 0.3 of the shiro-oauth plugin introduces a breaking change. The
`OAuthController.linkAccount` action no longer accepts a `userId` parameter.
Instead, you can:

* Pass `username` and `password` parameters for the account to link to.
* Pre-authenticate the target account (via `subject.login()`) before forwarding
  to the `linkAccount` action.

This breaking change was required to fix a security hole.
"""
