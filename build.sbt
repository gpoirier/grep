import scalariform.formatter.preferences._

name := "grep"

version := "1.0"

scalaVersion := "2.11.4"

resolvers += "Linter Repository" at "https://hairyfotr.github.io/linteRepo/releases"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.6" % "test"

scalacOptions ++= Seq("-feature", "-deprecation", "-Xlint", "-Xfatal-warnings")

addCompilerPlugin("com.foursquare.lint" %% "linter" % "0.1-SNAPSHOT")

wartremoverErrors ++= Warts.allBut(Wart.NoNeedForMonad)

defaultScalariformSettings

com.github.retronym.SbtOneJar.oneJarSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignParameters, false)
  .setPreference(AlignSingleLineCaseStatements, false)
  .setPreference(CompactControlReadability, false)
  .setPreference(CompactStringConcatenation, false)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(FormatXml, true)
  .setPreference(IndentLocalDefs, false)
  .setPreference(IndentPackageBlocks, true)
  .setPreference(IndentSpaces, 2)
  .setPreference(IndentWithTabs, false)
  .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
  .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, false)
  .setPreference(PreserveDanglingCloseParenthesis, true)
  .setPreference(PreserveSpaceBeforeArguments, false)
  .setPreference(RewriteArrowSymbols, false)
  .setPreference(SpaceBeforeColon, false)
  .setPreference(SpaceInsideBrackets, false)
  .setPreference(SpaceInsideParentheses, false)
  .setPreference(SpacesWithinPatternBinders, true)
  .setPreference(SpacesWithinPatternBinders, false)
