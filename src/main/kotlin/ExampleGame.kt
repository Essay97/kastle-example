import io.github.essay97.kastle.dsl.game
import io.github.essay97.kastle.dto.GameConfiguration
import io.github.essay97.kastle.dto.LinkState
import io.github.essay97.kastle.model.LinkBehavior
import io.github.essay97.kastle.service.GameProvider
import kotlinx.datetime.LocalDate

class ExampleGame : GameProvider {
    override fun provideConfiguration(): GameConfiguration = game("r-start") {

        metadata {
            name = "Example Game"
            author = "Enrico Saggiorato"
            version = "1.0.0"
            published = LocalDate(2024, 5, 30)
        }

        preface = """
        This is the preface of this game.
        As you can see here, by exploiting Kotlin multiline strings, we can provide a quite long text as a preface,
        so in a real game we would have plenty of space to contextualize our game!
        """.trimIndent()

        room("r-start") {
            name = "Initial room"
            description = "This is the first room that the player sees"
            north("r-next") {
                behavior = LinkBehavior.CONSTANT
                state = LinkState.OPEN
            }

            item("i-table") {
                name = "Table"
                description = "A nice wooden table"
            }

            item("i-medal") {
                name = "Medal"
                description = "This medal wins the game!"
            }

            item("i-sword") {
                name = "Sword"
                matchers("blade", "weapon")
                storable = true
            }

            character("c-doorman") {
                name = "Jack"
                description = "Jack is the official Kastle's doorman!"

                dialogue {
                    firstQuestion("d-first") {
                        text = "Hey player! are you ready for the adventure?"
                        answer {
                            text = "Yes!"
                            nextQuestion = "d-ifyes"
                        }
                        answer {
                            text = "No..."
                            nextQuestion = "d-ifno"
                        }
                    }

                    question("d-ifyes") {
                        text = "Ok, take this and go the the next room!"
                        reward("i-diploma") {
                            name = "Diploma"
                            description = """
                    It's a nice diploma with decorated borders. It says:
                    'Well done, player!

                    You reached the end of your first Kastle game.
                    Move to the north to win the game!'
                """.trimIndent()
                        }
                    }

                    question("d-ifno") {
                        text = "No problem, I'll wait until you're ready, but you're missing a lot of fun!"
                    }
                }
            }
        }

        room("r-next") {
            name = "Another room"
            description = "This is a room that the player can move to"
        }

        winIf {
            playerEnters = "r-next"
        }

        player {
            name = "Enrico"
            description = "Enrico is the author of Kastle and also an awesome hero for this game"
        }
    }
}