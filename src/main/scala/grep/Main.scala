package grep

import java.io.File

object Main extends App {

  object ErrorCodes {
    val empty = 1
    val badArguments = 2
  }

  if (args.length != 2) {
    println("Usage: grep <word> <folder>")
    sys.exit(ErrorCodes.badArguments)
  } else {
    val word = args(0)
    val folder = new File(args(1))
    if (!folder.exists()) {
      println(s"The folder $folder does not exists.")
      sys.exit(ErrorCodes.badArguments)
    } else if (!folder.isDirectory) {
      println(s"Invalid folder: $folder")
      sys.exit(ErrorCodes.badArguments)
    }
    val files = Grep.filterByWord(word, folder)
    if (files.isEmpty) {
      println(s"The word '$word' cannot be found in any file inside the folder $folder")
      sys.exit(ErrorCodes.empty)
    } else {
      files.foreach(println(_))
    }
  }

}
