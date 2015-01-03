package grep

import scala.io.{ Codec, Source }
import java.io.File

import Codec.UTF8

object Grep {

  /**
   * Scan all the files contained in the provided folder, and return those that
   * contains the provided word.
   * @param word the word to look search.
   * @param folder the folder from where to list files
   * @return the list of files with the specified word
   */
  def filterByWord(word: String, folder: File): Set[File] =
    listFiles(folder).par.filter { file =>
      Source.fromFile(file).withLines(_.exists(lineContainsWord(word, _)))
    }.seq

  def listFiles(root: File): Set[File] =
    Option(root.listFiles).fold(Set(root))(
      _.toSet.flatMap(listFiles)
    )

  def lineContainsWord(word: String, line: String): Boolean =
    raw"(?i)\b${word}\b".r.findFirstIn(line).isDefined

  implicit class SourceHelper(val source: Source) extends AnyVal {

    /**
     * Create an iterator for the source's lines,
     * transform it through the function `f` and
     * then close the source.
     */
    def withLines[T](f: Iterator[String] => T): T = {
      try {
        f(source.getLines())
      } finally {
        source.close()
      }
    }

  }
}
