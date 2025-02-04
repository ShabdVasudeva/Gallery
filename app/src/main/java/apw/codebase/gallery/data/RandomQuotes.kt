package apw.codebase.gallery.data;

import kotlin.random.Random

class RandomQuotes {

    val quotesList = listOf(
        Quotes("The best way to predict the future is to create it.", "Abraham Lincoln"),
        Quotes("You miss 100% of the shots you don't take.", "Wayne Gretzky"),
        Quotes("Success is not final, failure is not fatal: It is the courage to continue that counts.", "Winston Churchill"),
        Quotes("The purpose of life is not to be happy. It is to be useful, to be honorable, to be compassionate.", "Ralph Waldo Emerson"),
        Quotes("It always seems impossible until it's done.", "Nelson Mandela"),
        Quotes("You can't go back and change the beginning, but you can start where you are and change the ending.", "C.S. Lewis"),
        Quotes("In three words I can sum up everything I've learned about life: It goes on.", "Robert Frost"),
        Quotes("Don't cry because it's over, smile because it happened.", "Dr. Seuss"),
        Quotes("Be yourself; everyone else is already taken.", "Oscar Wilde"),
        Quotes("Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.", "Albert Einstein"),
        Quotes("I'm selfish, impatient and a little insecure. I make mistakes, I am out of control and at times hard to handle. But if you can't handle me at my worst, then you sure as hell don't deserve me at my best.","Marilyn Monroe"),
        Quotes("So many books, so little time.","Frank Zappa"),
        Quotes("Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.","Albert Einstein"),
        Quotes("A room without books is like a body without a soul.","Marcus Tullius Cicero"),
        Quotes("You know you're in love when you can't fall asleep because reality is finally better than your dreams.","Dr. Seuss"),
        Quotes("You only live once, but if you do it right, once is enough.", "Mae West"),
        Quotes("Be the change that you wish to see in the world.", "Mahatma Gandhi"),
        Quotes("In three words I can sum up everything I've learned about life: it goes on.", "Robert Frost"),
        Quotes("If you want to know what a man's like, take a good look at how he treats his inferiors, not his equals.", "J.K. Rowling"),
        Quotes("If you tell the truth, you don't have to remember anything.", "Mark Twain"),
        Quotes("To live is the rarest thing in the world. Most people exist, that is all.", "Oscar Wilde"),
        Quotes("A friend is someone who knows all about you and still loves you.", "Elbert Hubbard"),
        Quotes("Always forgive your enemies; nothing annoys them so much.", "Oscar Wilde"),
        Quotes("Live as if you were to die tomorrow. Learn as if you were to live forever.", "Mahatma Gandhi"),
        Quotes("We accept the love we think we deserve.", "Stephen Chbosky"),
        Quotes("Without music, life would be a mistake.", "Friedrich Nietzsche"),
        Quotes("I am so clever that sometimes I don't understand a single word of what I am saying.", "Oscar Wilde"),
        Quotes("It is better to be hated for what you are than to be loved for what you are not.", "Andre Gide"),
        Quotes("When love hurts you, dare to love again.", "Unkown"),
        Quotes("When life seems to beat you down, dare to fight back.", "Unkown"),
        Quotes("When times are tough, dare to be tougher.", "Unkown"),
        Quotes("Imperfection is beauty, madness is genius and it's better to be absolutely ridiculous than absolutely boring.", "Marilyn Monroe"),
        Quotes("Try not to become a man of success. Rather become a man of value.", "Albert Einstein"),
        Quotes("I have not failed. I've just found 10,000 ways that won't work.", "Thomas A. Edison"),
        Quotes("A day without sunshine is like, you know, night.", "Steve Martin"),
        Quotes("I love you as certain dark things are loved, secretly, between the shadow and the soul.", "Pablo Neruda"),
        Quotes("Hardwork never bring fatigue, it brings satisfaction.", "Narendra Modi"),
        Quotes("Once we decide we have to do somethings, we can go miles ahead", "Narendra Modi"),
        Quotes("There is no real beauty without some slight imperfection.", "Unkown"),
        Quotes("The pessimist sees difficulty in every opportunity.", "Unkown"),
        Quotes("Beauty begins the moment you decide to be yourself.", "Coco Chanel"),
        Quotes("Every moment is enormous and it is all we have.", "Natalie Goldberg"),
        Quotes("You will face many defeats in life, but never let yourself be defeated.", "Maya Angelou"),
        Quotes("Life is a long lesson in humility.", "J.M. Barrie"),
        Quotes("The whole secret of a successful life is to find out what is one's destiny to do, and then do it.", "Henry Ford"),
        Quotes("Life has no limitations, except the ones you make.", "Les Brown"),
        Quotes("All you need in this life is ignorance and confidence; then success is sure.", "Mark Twain"),
        Quotes("Life is like riding a bicycle. To keep your balance, you must keep moving.", "Albert Einstein"),
        Quotes("A life is not important except in the impact it has on other lives.", "Jackie Robinson")
    )
    
    fun getRandomQuote(): Quotes{
        return quotesList[Random.nextInt(quotesList.size)]
    }
}