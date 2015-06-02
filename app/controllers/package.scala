package object controllers {
  def DB = play.api.db.DB
  implicit def current = play.api.Play.current
  implicit def global = scala.concurrent.ExecutionContext.Implicits.global

  implicit val dateWrites = play.api.libs.json.Writes.dateWrites("yyyy-MM-dd'T'HH:mm:ssZ")
}
