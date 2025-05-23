error id: A8B8A9BF119CE61B80C9D98D294ED608
file://<WORKSPACE>/src/Main/Scala/Main.scala
### java.lang.AssertionError: assertion failed: file://<WORKSPACE>/src/Main/Scala/Main.scala: 19 >= 19

occurred in the presentation compiler.



action parameters:
offset: 19
uri: file://<WORKSPACE>/src/Main/Scala/Main.scala
text:
```scala

@main def main(): @@
```


presentation compiler configuration:
Scala version: 2.12.20
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.12.20/scala-library-2.12.20.jar [exists ]
Options:





#### Error stacktrace:

```
scala.reflect.internal.util.SourceFile.position(SourceFile.scala:33)
	scala.tools.nsc.CompilationUnits$CompilationUnit.position(CompilationUnits.scala:133)
	scala.meta.internal.pc.SignatureHelpProvider.signatureHelp(SignatureHelpProvider.scala:25)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$signatureHelp$1(ScalaPresentationCompiler.scala:423)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: file://<WORKSPACE>/src/Main/Scala/Main.scala: 19 >= 19