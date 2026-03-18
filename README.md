# Laboratory Work 3: Lexer & Scanner

**Course:** Formal Languages & Finite Automata  
**Author:** Chiril  

---

## 1. Overview and Theory

**Lexical Analysis** is the first phase of a compiler or interpreter. It represents the process of reading a stream of raw characters (source code or input strings) and grouping them into meaningful, structured sequences called **lexemes**. 

The program responsible for this phase is called a **Lexer**, **Scanner**, or **Tokenizer**. 
- A **lexeme** is the actual string of characters extracted from the input (e.g., `3.14`, `+`, `sin`).
- A **token** is the abstract category or label assigned to that lexeme (e.g., `FLOAT`, `PLUS`, `IDENTIFIER`). 

By converting raw text into a stream of categorized tokens, the lexer removes irrelevant data (like whitespace or comments) and prepares the input for the next phase of compilation (Syntax Analysis/Parsing).

## 2. Objectives

1. Understand the core concepts of lexical analysis and the distinction between lexemes and tokens.
2. Get familiar with the inner workings of a scanner by building one from scratch.
3. **Advanced Implementation:** Go beyond a basic calculator lexer by implementing logic to correctly identify and separate:
   - Integers vs. Floating-point numbers.
   - Standard mathematical operators.
   - Trigonometric functions (specifically `sin` and `cos`).

---

## 3. Project Structure & Implementation Details

The project is implemented in **Java** using a clean, object-oriented approach without any external parsing libraries. It is divided into four main components:

### `TokenType.java`
An `enum` that strictly defines the vocabulary of our language. It includes specific types for advanced math, such as `INT`, `FLOAT`, `SIN`, and `COS`, alongside standard operators (`PLUS`, `MINUS`, `MULTIPLY`, `DIVIDE`, `LPAREN`, `RPAREN`), and an `EOF` (End of File) marker.

### `Token.java`
A simple data structure (POJO) that binds a defined `TokenType` to its corresponding `literal` (the raw lexeme string). It overrides the `toString()` method for clean console output.

### `Lexer.java`
The core engine of the scanner. 
- **Character Processing:** It reads the input string character by character, maintaining a `position` and a `readPosition` pointer. 
- **Whitespace Handling:** The `skipWhitespace()` method ensures that spaces, tabs, and line breaks are ignored, as they hold no mathematical meaning in this context.
- **Dynamic Tokenization (`nextToken`):** - Single-character operators (like `+` or `*`) are matched instantly using a `switch` statement.
  - **Numbers:** If a digit is encountered, the `readNumber()` method consumes characters until the number ends. It contains logic to check for the presence of a decimal point (`.`), allowing it to dynamically classify the lexeme as either an `INT` or a `FLOAT`.
  - **Identifiers:** If a letter is encountered, the `readIdentifier()` method consumes the string and checks it against known trigonometric keywords (`sin`, `cos`). If it doesn't match, it returns an `ILLEGAL` token.

### `Main.java`
The client class that initializes the Lexer with a complex mathematical string and loops through the generated tokens until the `EOF` token is reached, printing the results to the console.

---

CONSOLE:
--- Advanced Math Lexer ---
Input String: cos(3.14) * 52 - sin(0.5) + 10 / 2
---------------------------

Token{type=COS        literal='cos'}
Token{type=LPAREN     literal='('}
Token{type=FLOAT      literal='3.14'}
Token{type=RPAREN     literal=')'}
Token{type=MULTIPLY   literal='*'}
Token{type=INT        literal='52'}
Token{type=MINUS      literal='-'}
Token{type=SIN        literal='sin'}
Token{type=LPAREN     literal='('}
Token{type=FLOAT      literal='0.5'}
Token{type=RPAREN     literal=')'}
Token{type=PLUS       literal='+'}
Token{type=INT        literal='10'}
Token{type=DIVIDE     literal='/'}
Token{type=INT        literal='2'}
Token{type=EOF        literal=''}
