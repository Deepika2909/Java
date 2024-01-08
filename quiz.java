import java.util.*;

enum QuestionType {
    MULTIPLE_CHOICE,
    TRUE_FALSE,
    OPEN_ENDED
}

class Question {
    String text;
    QuestionType type;
    String correctAnswer;

    public Question(String text, QuestionType type, String correctAnswer) {
        this.text = text;
        this.type = type;
        this.correctAnswer = correctAnswer;
    }
}

class Quiz {
    List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}

class QuizSession {
    Map<Question, String> userAnswers;

    public QuizSession() {
        this.userAnswers = new HashMap<>();
    }

    public void answerQuestion(Question question, String answer) {
        userAnswers.put(question, answer);
    }
}

class QuizResult {
    int score;

    public QuizResult(int score) {
        this.score = score;
    }
}

class QuizResultService {
    public QuizResult calculateResult(QuizSession quizSession, Quiz quiz) {
        int score = 0;

        for (Question question : quizSession.userAnswers.keySet()) {
            String userAnswer = quizSession.userAnswers.get(question);
            if (userAnswer.equals(question.correctAnswer)) {
                score++;
            }
        }

        return new QuizResult(score);
    }
}

class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample quiz creation
        Quiz quiz = new Quiz();
        quiz.addQuestion(new Question("What is the capital of France?", QuestionType.OPEN_ENDED, "Paris"));
        quiz.addQuestion(new Question("Is the earth flat?", QuestionType.TRUE_FALSE, "False"));
        quiz.addQuestion(new Question("Which programming language is used for Android development?",
                QuestionType.MULTIPLE_CHOICE, "Java"));
        quiz.addQuestion(
                new Question("Who wrote the play Romeo and Juliet? ", QuestionType.OPEN_ENDED, "William Shakespeare"));
        quiz.addQuestion(new Question("What is the largest big cat in the world?", QuestionType.OPEN_ENDED, "Tiger"));
        quiz.addQuestion(
                new Question("In which sport would you perform a slam dunk?", QuestionType.OPEN_ENDED, "Basketball"));
        quiz.addQuestion(new Question("Who is the co-founder of Microsoft along with Bill Gates?",
                QuestionType.OPEN_ENDED, "Paul Allen"));
        quiz.addQuestion(new Question("In which year did Christopher Columbus reacah the Americas?",
                QuestionType.OPEN_ENDED, "1492"));
        quiz.addQuestion(new Question("Name the longest river in the world.", QuestionType.OPEN_ENDED, "Nile River"));
        quiz.addQuestion(new Question("What is the capital city of Japan?", QuestionType.OPEN_ENDED, "Tokyo"));

        // User authentication (simplified)
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Sample authentication (username: admin, password: admin)
        if (!username.equals("admin") || !password.equals("admin")) {
            System.out.println("Authentication failed. Exiting...");
            return;
        }

        // Quiz session
        QuizSession quizSession = new QuizSession();

        // Quiz taking
        for (Question question : quiz.questions) {
            System.out.println(question.text);

            // Get user's answer
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            quizSession.answerQuestion(question, userAnswer);
        }

        // Calculate and display results
        QuizResultService resultService = new QuizResultService();
        QuizResult result = resultService.calculateResult(quizSession, quiz);

        System.out.println("Quiz completed. Your score: " + result.score + "/" + quiz.questions.size());

        scanner.close();
    }
}
