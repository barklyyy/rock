package com.example.rock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    enum class Choice {
        ROCK {
            override fun beats(): List<Choice> = listOf(Choice.SCISSORS, Choice.LIZARD)
            override fun losesTo(): List<Choice> = listOf(Choice.PAPER, Choice.SPOCK)
        },
        PAPER {
            override fun beats(): List<Choice> = listOf(Choice.ROCK, Choice.SPOCK)
            override fun losesTo(): List<Choice> = listOf(Choice.SCISSORS, Choice.LIZARD)
        },
        SCISSORS {
            override fun beats(): List<Choice> = listOf(Choice.PAPER, Choice.LIZARD)
            override fun losesTo(): List<Choice> = listOf(Choice.ROCK, Choice.SPOCK)
        },
        LIZARD {
            override fun beats(): List<Choice> = listOf(Choice.PAPER, Choice.SPOCK)
            override fun losesTo(): List<Choice> = listOf(Choice.ROCK, Choice.SCISSORS)
        },
        SPOCK {
            override fun beats(): List<Choice> = listOf(Choice.ROCK, Choice.SCISSORS)
            override fun losesTo(): List<Choice> = listOf(Choice.PAPER, Choice.LIZARD)
        };

        abstract fun beats(): List<Choice>
        abstract fun losesTo(): List<Choice>
    }

    class Game {
        private var playerChoice: Choice? = null
        private var computerChoice: Choice? = null

        fun playerChooses(choice: Choice) {
            playerChoice = choice
            computerChooses()
        }

        private fun computerChooses() {
            val values = Choice.values()
            computerChoice = values[Random.nextInt(values.size)]
        }

        fun getWinner(): String {
            if (playerChoice == null || computerChoice == null) {
                return "Ничья"
            }
            if (playerChoice == computerChoice) {
                return "Ничья"
            }
            if (playerChoice!!.beats().contains(computerChoice)) {
                return "Вы победили!"
            }
            return "Вы проиграли."
        }
    }
    private val game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonRock).setOnClickListener {
            game.playerChooses(Choice.ROCK)
            showWinner(game.getWinner())
        }

        findViewById<Button>(R.id.buttonPaper).setOnClickListener {
            game.playerChooses(Choice.PAPER)
            showWinner(game.getWinner())
        }

        findViewById<Button>(R.id.buttonScissors).setOnClickListener {
            game.playerChooses(Choice.SCISSORS)
            showWinner(game.getWinner())
        }

        findViewById<Button>(R.id.buttonLizard).setOnClickListener {
            game.playerChooses(Choice.LIZARD)
            showWinner(game.getWinner())
        }

        findViewById<Button>(R.id.buttonSpock).setOnClickListener {
            game.playerChooses(Choice.SPOCK)
            showWinner(game.getWinner())
        }
    }

    private fun showWinner(winner: String) {
        Toast.makeText(this, winner, Toast.LENGTH_SHORT).show()
    }

}
