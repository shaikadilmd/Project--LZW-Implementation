/***

@name Shaik Kamal Mohammed Adil	

@studentID 801151613

***/

					ITCS 6114/8114: Algorithms and Data Structures
					Programming Project: LZW Compression

# Introduction:

The Lempel–Ziv–Welch (LZW) algorithm is a lossless data compression algorithm. LZW is an adaptive compression algorithm that does not assume prior knowledge of the input data distribution. This algorithm works well when the input data is sufficiently large and there is redundancy in the data. The algorithm is simple to implement and has the potential for very high throughput in hardware implementations.

# Application of LZW

1. GIF and TIFF files
2. Adobe Acrobat Softwaare

# Two steps of LZW Algorithm
1. Encoding/Compressing
2. Decoding/Decompressing

# LZW Algorithm
### Input File --> Compressed File --> Decompressed File
First arrow shows the encoding done by Compressing.java
Second arrow shows the compressed file is decoded to original text using Decompressing.java


#### Pseudocode of Encoding

~~~
MAX_TABLE_SIZE=2(bit_length) //bit_length is number of encoding bits
initialize TABLE[0 to 255] = code for individual characters
STRING = null
while there are still input symbols:
SYMBOL = get input symbol
if STRING + SYMBOL is in TABLE:
STRING = STRING + SYMBOL
else:
output the code for STRING
If TABLE.size < MAX_TABLE_SIZE: // if table is not full
add STRING + SYMBOL to TABLE // STRING + SYMBOL now has a code
STRING = SYMBOL
output the code for STRING
~~~

#### Pseudocode of Decoding

~~~
MAX_TABLE_SIZE=2(bit_length)
initialize TABLE[0 to 255] = code for individual characters
CODE = read next code from encoder
STRING = TABLE[CODE]
output STRING
while there are still codes to receive:
CODE = read next code from encoder
if TABLE[CODE] is not defined: // needed because sometimes the
NEW_STRING = STRING + STRING[0] // decoder may not yet have code!
else:
NEW_STRING = TABLE[CODE]
output NEW_STRING
if TABLE.size < MAX_TABLE_SIZE:
add STRING + NEW_STRING[0] to TABLE
STRING = NEW_STRING
~~~


# Data Structure
HashMap data structure is used to implement the algorithm and it contains the ASCII characters as KEY along with its ascii value as VALUE for encoding and vice versa in case of decoding.

# Compressing.java
~~~
The Compressing class uses HashMap and follows Pseudocode of Encoding to encode the input file. Here, In HashMap ASCII Character is the KEY and ASCII Value is the VALUE.
~~~
# Decompressing.java
~~~
The Decompressing class uses HashMap in opposite fashion, where ASCII Value is the KEY and ASCII Character is the VALUE. It follows the Pseudocode of Decoding to decipher the encoded text.
~~~

# Note
1. BufferedReader and BufferedWriter for reading and writing to files.
2. For encoding, Compressing.java file is used. It will generate compressed (lzw) file.
3. For decoding compressed file, Decompressing.java is used. It will generate decoded text file, whose contents will be same as the initial input file.
4. The compression file is created using charset UTF_16BE and stored in 16-bit format.


# What works?
The Compression program generates the compressed file in a 16bit (UTF-16BE) format. The Decompresser program converts the generated compressed file back to the original input file. It handles null files and we can observe that large file size after compression is reduced to tiny files.


# References:
1. https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch 
2. www.asciitable.com
