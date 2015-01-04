package grep

import java.io.File

object Main extends App {

  case class Config(word: Option[String] = None, folder: Option[File] = None)

  object ErrorCodes {
    val empty = 1
    val badArguments = 2
  }

  val parser = new scopt.OptionParser[Config]("grep.sh") {
    head("grep looks through all the files contained in a folder for a specific word and output the files that contain it.")

    arg[String]("<word>") required() action { (text, config) =>
      config.copy(word = Some(text))
    } text "The word to search"

    arg[File]("<folder>") required() action { (folder, config) =>
      config.copy(folder = Some(folder))
    } validate { case folder =>
      if (!folder.exists)
        failure(s"The folder $folder does not exists.")
      else if (!folder.isDirectory)
        failure(s"Invalid folder: $folder")
      else
        success
    } text "The folder to scan"
  }

  parser.parse(args, Config()) match {
    case Some(config) =>
      val word = config.word.getOrElse { throw new AssertionError("word is mandatory") }
      val folder = config.folder.getOrElse { throw new AssertionError("folder is mandatory") }

      val files = Grep.filterByWord(word, folder)
      if (files.isEmpty) {
        println(s"The word '$word' cannot be found in any file inside the folder $folder")
        sys.exit(ErrorCodes.empty)
      } else {
        files.foreach(println(_))
      }
    case None =>
      sys.exit(ErrorCodes.badArguments)
  }
}
