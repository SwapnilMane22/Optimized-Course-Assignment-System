# CS542: Assignment 3
## Name: Swapnil Tukaram Mane

-----------------------------------------------------------------------
-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in /swapnil_mane_assign3/studentCourseSequencer/src/build.xml.

-----------------------------------------------------------------------
## Instruction to clean:

#### Command: ant -buildfile swapnil_mane_assign3/studentCourseSequencer/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

#### Command: ant -buildfile swapnil_mane_assign3/studentCourseSequencer/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Command: ant -buildfile swapnil_mane_assign3/studentCourseSequencer/src/build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=errorLog.txt -Darg3=Logger.txt -Darg4=0

## Replace <fileName.txt> with real file names. The run command requires total 5 arguments: 
## args[0]: Input file name
## args[1]: Output file name
## args[2]: Error logger file path
## args[3]: Logger file path
## args[4]: Debug level (0, 1, 2, 3)

## For example, if the files are available in the path,
## you can run it in the following manner:

ant -buildfile swapnil_mane_assign3/studentCourseSequencer/src/build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=errorLog.txt -Darg3=Logger.txt -Darg4=0

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
#### No. of Slack Days used: 03
#### Total Slack Days used so far: 04 (including 01 slack day used for assignment 2)
-----------------------------------------------------------------------
## Description:
This Java program manages the graduation timeline for students pursuing a degree in Computer Science. It uses the State pattern to capture degree requirements and efficiently assigns courses to students while considering prerequisites and constraints. The program ensures that each student completes the required number of courses in different groups and maintains a wait-list for courses that cannot be immediately assigned. It also stops processing courses for students once they become eligible for graduation.

The waitlist in the CourseRegistrationHelper class is implemented using a Queue data structure. The use of a Queue (implemented as a LinkedList) is suitable for managing a waitlist where the order of addition and removal is crucial, as it ensures that the first course added is the first one processed when spots become available. The algorithm processes the waitlist every time before assigning the input course from the file. This ensures the priority is given to the courses in the waitlist and helps process all the courses from the input file as per student preferences. Additionally, it is important to note that a course and its pre-requisite/s cannot be assigned to a student in the same semester. Therefore, a course, if put into the wait-list, cannot be removed until at least the current semester is over.

### Graduation Requirements:
Group-1: Long Programming and Design - 2 courses from A-D
Group-2: Data Structures and Algorithms - 2 courses from E-H
Group-3: Hardware Sequence - 2 courses from I-L
Group-4: Data Analytics - 2 courses from M-P
Group-5: Electives - 2 courses from Q-Z

### Student States:
Each student can be in one of the following states based on their course preferences:

StateOne: Most courses in Group-1
StateTwo: Most courses in Group-2
StateThree: Most courses in Group-3
StateFour: Most courses in Group-4
StateFive: Most courses in Group-5

### Course Assignment:
Students can take at most 3 courses per semester.
Courses in a group must be taken in alphabetic order (except Group-5).
Courses with prerequisites are saved in a wait-list.

### Eligibility for Graduation:
To graduate, a student must:

Complete 10 courses.
Take 2 courses from each group.

### Input Format:
The program accepts a single input file with the format: 

<studentID>: <course> <course> <course> ... <course>

### Output Format:
The program stores results in the output file in the format: 

<studentID> <course completed> <course completed> ... <course completed> -- <# semesters> <# state changes>

If a student does not graduate, it writes a line at the end indicating this, with # semesters set to 0.

## Usage:

1. Prepare Input File: Create an input file (e.g., input.txt) with the preferred sequence of courses for each student. The format should be: <studentID>: <course> <course> <course> ... <course>

2. Compile the Code: Use the provided ANT commands to compile the code. Run the following command in the project directory: ant -buildfile swapnil_mane_assign3/studentCourseSequencer/src/build.xml all

3. Run the Program: Execute the program using the following command: ant -buildfile swapnil_mane_assign3/studentCourseSequencer/src/build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=errorLog.txt

4. The program will process the input file and generate an output file (output.txt) with the results. Each line in the output file represents a student's course sequence, the number of semesters it took, and the number of state changes.

Use the "Course Sequence Manager for Computer Science Graduates" project to manage the graduation timeline for students in a Computer Science degree program, assign courses efficiently, and track their progress toward graduation.
-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.""

Date: November 17th, 2023