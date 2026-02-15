Connect 4 AI Agent 🎮
A Java-based AI that plays Connect 4 using the Minimax algorithm with Alpha-Beta pruning.

🚀 Features
Minimax with Alpha-Beta pruning for efficient move searching

Smart evaluation with center-weighted columns and exponential row priority

Threat detection (spots 2-in-a-row and 3-in-a-row patterns)

Three game modes: AI vs AI, Human vs AI, Human vs Human

🎯 How It Works
The AI evaluates positions using:

Column weights: [1, 2, 4, 5, 4, 2, 1] (center preference)

Row weights: [1, 2, 4, 8, 16, 32] (bottom priority)

Threat scoring: +100 for my 3-in-a-row, -200 for opponent's 3-in-a-row

📦 Getting Started
bash
# Compile
javac *.java

# Run
java Game
Choose game mode and play!

🧠 AI Strength
At depth 6+, the AI provides a solid challenge for most players. It detects forced wins, blocks opponent threats, and maintains strategic position.

📁 Files
Game.java - Main game logic

Joueur.java - Player base class

JoueurAI.java - AI implementation

Colors.java - Console formatting

🔧 Customize
Adjust difficulty by changing search depth in getBestMove():

java
int move = ai.getBestMove(game, 6); // Change 6 to 4 (easier) or 8 (harder)
Try to beat it! 🏆
