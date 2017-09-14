# sentiment-analysis
Analyze the sentiment (positive or negative) of a sentence based on the words it contains 

Sentiment analysis is a task from the field of computational linguistics that seeks to determine the general attitude of a given piece of text.
For instance, we would like to have a program that could look at the text “This assignment was joyful and a pleasure” and 
realize that it was a positive statement while “It made me want to pull out my hair” is negative.

One algorithm that we can use for this is to assign a numeric value to any given word based on how positive or negative that word is 
and then determine the overall sentiment of the statement based on the average values of the words.

To determine the sentiment of an individual word, we can use a corpus of statements,
each of which has an overall score already assigned to it. The sentiment of an individual word equals the average of the statements in which that word appears.
