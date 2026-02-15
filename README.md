Connect 4 AI Agent 🎮
https://img.shields.io/badge/Java-17%252B-orange
https://img.shields.io/badge/License-MIT-blue

A Java-based artificial intelligence agent that plays Connect 4 using the Minimax algorithm with Alpha-Beta pruning. The AI evaluates positions using sophisticated heuristics for center control, row priority, and threat detection.

https://demo.gif <!-- Add a demo GIF here -->

📋 Table of Contents
Features

How It Works

Getting Started

Game Modes

AI Strategy

Performance

Project Structure

Customization

Future Improvements

Contributing

License

✨ Features
Core AI
✅ Minimax Algorithm with Alpha-Beta pruning for efficient game tree search

✅ Dynamic Evaluation Function balancing offense and defense

✅ Threat Detection identifying 2-in-a-row and 3-in-a-row patterns

✅ Randomized Move Selection among equally optimal choices

Evaluation Heuristics
java
int[] colWeight = {1, 2, 4, 5, 4, 2, 1};  // Center-weighted columns
int[] rowWeight = {1, 2, 4, 8, 16, 32};    // Exponential row priority
Threat Detection Scoring
Threat Type	Score	Action
🟢 My 3-in-a-row	+100	Winning threat
🟢 My 2-in-a-row	+10	Developing threat
🔴 Opponent 3-in-a-row	-200	BLOCK IMMEDIATELY!
🔴 Opponent 2-in-a-row	-20	Potential danger
🏆 Win/Loss	±100000 ± depth	Terminal node
🔍 How It Works
The Decision Process
text
┌─────────────────────────────────────────────────────────┐
│                     JOUEUR AI                            │
├───────────────┬──────────────────────┬───────────────────┤
│   PLANIFIER   │       DÉCIDER        │     EXÉCUTER      │
├───────────────┼──────────────────────┼───────────────────┤
│ minimax()     │ getBestMove()        │ setCoup()         │
│ evaluatePosition() │ Compare scores   │ Update board      │
│ Alpha-Beta    │ Select best move     │                   │
│ Threat detection │ Handle ties       │                   │
└───────────────┴──────────────────────┴───────────────────┘
1. Planning Phase (minimax)
Explores future game states up to a specified depth, evaluating each possible sequence of moves.

2. Decision Phase (getBestMove)
Analyzes all possibilities and selects the move with the highest score. When multiple moves tie, chooses randomly for variety.

3. Execution Phase (setCoup)
Plays the chosen move on the game board.

🚀 Getting Started
Prerequisites
Java JDK 8 or higher

Git (optional)

Installation
bash
# Clone the repository
git clone https://github.com/yourusername/connect4-ai-agent.git

# Navigate to project directory
cd connect4-ai-agent

# Compile the Java files
javac *.java

# Run the game
java Game
🎮 Game Modes
text
1. AI vs AI     - Watch two AIs compete
2. Human vs AI  - Test your skills against the agent
3. Human vs Human - Two-player mode
🧠 AI Strategy
Why This AI Is Strong
Deep Search: At depth 6+, evaluates millions of positions before each move

Center Control: Prefers center columns (col 3 weighted 5x more than edges)

Bottom Priority: Exponential row weights (bottom row = 32x more valuable than top)

Threat Awareness: Detects and prioritizes blocking opponent 3-in-a-rows

Perfect Endgame: Recognizes forced wins/losses with ±100000 scoring

Sample Game Analysis
text
Move 1 (Player 1): -128  ← Slight disadvantage (first player)
Move 2 (Player 2): -203  ← Defending
...
Move 15 (Player 1): 100004 ← FORCED WIN DETECTED! 🏆
📊 Performance
Search Depth	Nodes Evaluated	Playing Strength
Depth 4	~10⁴	Beats casual players
Depth 6	~10⁶	Beats experienced players
Depth 8	~10⁸	Near-perfect play
At depth 6+, the AI provides a challenging opponent for most human players.

📁 Project Structure
text
├── Game.java          # Main game logic and board management
├── Joueur.java        # Player base class
├── JoueurAI.java      # AI implementation with minimax
├── Colors.java        # Console color formatting
└── README.md          # Project documentation
Key Methods in JoueurAI.java
Method	Description
minimax()	Recursive game tree search with alpha-beta pruning
getBestMove()	Selects best move with tie-breaking
evaluatePosition()	Heuristic evaluation of board state
threatDetection()	Scans for 2-in-a-row and 3-in-a-row threats
evalWindow()	Analyzes individual 4-cell windows
⚙️ Customization
Tune the AI's behavior by adjusting these parameters in JoueurAI.java:

java
// Column weights (center preference)
int[] colWeight = {1, 2, 4, 5, 4, 2, 1};

// Row weights (bottom priority)
int[] rowWeight = {1, 2, 4, 8, 16, 32};

// Defensive multiplier
oppScore += (colWeight[j] + rowWeight[i]) * 2;  // Higher = more defensive

// Threat detection scaling
score += threatDetection(position, myId, oppId) * 10;  // Scale factor
🔮 Future Improvements
Transposition Tables - Cache evaluated positions

Opening Book - Pre-calculated optimal first moves

Iterative Deepening - Better time management

Killer Heuristic - Remember good moves for pruning

GUI Interface - Graphical board display

Monte Carlo Tree Search - Alternative algorithm

🤝 Contributing
Contributions are welcome! Here's how you can help:

Fork the repository

Create a feature branch (git checkout -b feature/amazing-feature)

Commit your changes (git commit -m 'Add amazing feature')

Push to the branch (git push origin feature/amazing-feature)

Open a Pull Request

🙏 Acknowledgments
Built to explore game theory, recursion, and optimization in Java

Inspired by classic game AI research

📬 Contact
aymenbencheikh789@gmail.com

Project Link: https://github.com/aymn-bc/Connect-4-AI-Agent

⭐ Star this repository if you found it interesting!
