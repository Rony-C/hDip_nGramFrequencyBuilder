# nGram Frequency Builder

Program Description

The nGramFrequencyBuilder program accepts user input to process files from a specified directory into ngrams of a specified size. The ngrams and their frequency are output to a CSV file.

An n-gram is a contiguous sequence of n items from a given sample of text or speech. Example:
String: “The quick brown fox” n-Gram size: 4
Output: “The “, “quic”, “k br”, etc.

Functions

A command line interface (CLI) allows the user to perform the following actions: 1) Specify a directory folder with files
2) Set a size for the n-Grams
3) Set an output file name
a) The output file will be created in the project folder as a CSV file
4) Create a CSV file using the details provided by the user
5) A quit program option is included so the user can terminate the program at any time

Notes

1) The size of the output file is dependant on the size of the ngram chosen by the user. This results in a lot of free space but can be easily changed to suit the programmers needs.
2) If an n-Gram is found that is smaller than the specified ngram size it is still added to the output file. This was intentional, in further revisions they may be excluded from the program.

Dependencies

OpenCSV is used as a reference library to be able to write the content to a CSV file. You will need to add this to your project according to your IDE settings. The dependencies can be found and downloaded from: http://opencsv.sourceforge.net/dependencies.html
An additional dependency for StringUtils from commons.apache.org need to be added. You can find and download them from here: https://commons.apache.org/proper/commons-lang/download_lang.cgi
 
