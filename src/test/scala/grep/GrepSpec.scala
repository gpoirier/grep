package grep

import java.io.File

import org.scalatest.{ FlatSpec, Matchers }

class GrepSpec extends FlatSpec with Matchers {

  val base = new File("test-data")
  val allFiles = Set(
    "test-data/Badges.xml",
    "test-data/Comments.xml",
    "test-data/Post/PostHistory.xml",
    "test-data/Post/PostLinks.xml",
    "test-data/Post/Posts.xml",
    "test-data/Post/Votes/Votes.xml",
    "test-data/Tags.xml",
    "test-data/Users.xml"
  )

  "listFiles" should "list all files" in {
    Grep.listFiles(base).map(_.toString) shouldBe allFiles
  }

  "filterByWord" should "find words in files" in {
    Grep.filterByWord("Row", base).map(_.toString) shouldBe allFiles
    Grep.filterByWord("row", base).map(_.toString) shouldBe allFiles

    Grep.filterByWord("site", base).map(_.toString) shouldBe Set(
      // "test-data/Badges.xml",
      "test-data/Comments.xml",
      "test-data/Post/PostHistory.xml",
      // "test-data/Post/PostLinks.xml",
      "test-data/Post/Posts.xml",
      // "test-data/Post/Votes/Votes.xml",
      // "test-data/Tags.xml",
      "test-data/Users.xml"
    )
  }

  "lineContainsWord" should "find a word in a line of text" in {
    Grep.lineContainsWord("site", "This is a good site for resting.") shouldBe true
    Grep.lineContainsWord("site", "This is a good site, even for resting.") shouldBe true
    Grep.lineContainsWord("site", "This is a good site.") shouldBe true
    Grep.lineContainsWord("site", "This is a good site") shouldBe true
    Grep.lineContainsWord("site", "This is a good Site") shouldBe true
    Grep.lineContainsWord("site", "This is a good-site") shouldBe true
    Grep.lineContainsWord("site", "This is a good <site> for resting.") shouldBe true
    Grep.lineContainsWord("site", "This is a good 'site' for resting.") shouldBe true
    Grep.lineContainsWord("site", "This is a good \"site\" for resting.") shouldBe true
    Grep.lineContainsWord("site", "Those are good sites for resting.") shouldBe false
  }
}
