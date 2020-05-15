#!/bin/bash
# Author: Tyler Rimaldi

javac Driver.java 
javac token/Token.java 
javac lexer/Lexer.java 

echo ""
echo ""
echo ""
echo "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~"
echo "        Welcome Prof. Rivas         "
echo "Enjoy some automated test cases for "
echo "you to have an easier time grading."
echo "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~"
echo ""
echo ""
echo ""
echo "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~"
echo "RUNNING TEST CASE 0: "
echo "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~"
java Driver -h

echo "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~"
echo "RUNNING TEST CASE 1: "
echo "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~"
java Driver -f ../../tests/test_case_1.txt

echo "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~"
echo "RUNNING TEST CASE 2: "
echo "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~"
java Driver -f ../../tests/test_case_2.txt

echo "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~"
echo "RUNNING TEST CASE 3: "
echo "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~"
java Driver -f ../../tests/test_case_3.txt

# echo "RUNNING TEST CASE 4: "
# java Driver -f ../../data/test_case4.txt

# echo "RUNNING TEST CASE 5: "
# java Driver -f ../../data/test_case5.txt

# echo "RUNNING TEST CASE 6: "
# java Driver -f ../../data/test_case6.txt
