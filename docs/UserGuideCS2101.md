User Guide
=======
SETA is a **desktop application for CS2103T Teaching Assistants** to track their students’ and tutorials’ details, and
questions asked by students. SETA is optimized for use via a Command Line Interface (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast, SETA enables you to track your students, manage your
tutorials and note down questions more effectively than traditional GUI apps.

The purpose of this guide is to inform you what features are available in SETA, and how to use them to solve your 
needs as a CS2103T TA.

<div markdown="block" class="alert alert-info">

**:information_source: What is a Command Line Interface (CLI)?**<br>

It is a programme that allows you to create, modify and delete files using a text-based display. In SETA, all 
you have to do is to type in the command box (red rectangle in the image below) and press enter to track your students 
and manage your questions and 
tutorials.

![CommandBox](images/ug-screenshots/commandbox.png)

</div>

* Table of Contents
    * **[Quick Start](#quick-start)**
    * **[Features](#features)**
        * **[Students](#students)**
            * Adding a student: [`addstu`](#adding-a-student--addstu)
            * Editing a student: [`editstu`](#editing-a-student-editstu)
            * Adding student's attendance: [`attendance`](#adding-students-attendance--attendance)
            * Adding student’s response: [`addresponse`](#adding-students-response-addresponse)
            * Adding help tag to a student: [`helpstu`](#adding-help-tag-helpstu)
            * Deleting a student: [`deletestu`](#deleting-a-student-deletestu)
            * Finding a student: [`findstu`](#finding-a-student-findstu)
            * Listing all students: [`liststu`](#listing-all-students-liststu)
        * **[Questions](#questions)**
            * Adding a question: [`addq`](#adding-a-question--addq)
            * Marking a question: [`markq`](#marking-a-question--markq)
            * Unmarking a question: [`unmarkq`](#unmarking-a-question--unmarkq)
            * Deleting a question: [`deleteq`](#deleting-a-question--deleteq)
        * **[Tutorials](#tutorials)**
            * Adding a tutorial: [`addtut`](#adding-a-tutorial--addtut)
            * Deleting a tutorial: [`deletetut`](#deleting-a-tutorial--deletetut)
            * Marking a tutorial: [`marktut`](#marking-a-tutorial-marktut)
            * Unmarking a tutorial: [`unmarktut`](#unmarking-a-tutorial-unmarktut)
        * **Exiting the program**: [`exit`](#exiting-the-program--exit)
    * **[FAQ](#faq)**
    * **[Command Summary](#command-summary)**

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) or 
   above installed in your Computer. If you have issues doing so, refer to the troubleshooting guide [here]
   (https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-0DB9580B-1ACA-4C13-8A83-9780BEDF30BB).

1. Download the latest `SETA.jar` from [here](https://github.com/AY2223S1-CS2103T-T08-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SETA.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.<br>
   ![Ui](images/ug-screenshots/UiStartup.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`liststu`** and pressing Enter will
   list all the students in the 'Student' column.<br>

Some example commands you can try:

* **`addstu`**`n/John Lim Jun Jie h/@johnlimjj e/johnlim@example.com` : Adds a student named `John Lim Jun Jie` to the
  student list.

* **`deletestu`**`3` : Deletes the 3rd student shown in the current list.

* **`exit`** : Exits the app.

Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

SETA contains features that allow you to manage 3 things:
1. [Students](#students)
2. [Questions](#questions)
3. [Tutorials](#tutorials)

------>>>>**insert why they need to do what here!!**

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addstu n/NAME`, `NAME` is a parameter which can be used as `addstu n/John Lim`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [h/TELEGRAM_HANDLE]` can be used as `n/John Lim h/@johnlim` or as `n/John Lim`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME e/EMAIL`, `e/EMAIL n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `h/@johnlim h/@johnlimjj`, only `h/@johnlimjj` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `liststu` and `exit`) will be ignored.<br>
  e.g. if the command specifies `liststu 123`, it will be interpreted as `liststu`.

</div>

### Students
Each student contains details such as their name, telegram handle and email. The latter two are there for you to 
contact the student if necessary. You can also track each student's attendance and include the number of messages 
he has sent on Zoom to note down his participation.

### Adding a student : `addstu`

Adds a student to the student list.
At the start of the semester, after gathering the student details, you can add each student into SETA to start 
tracking their participation.

Format: `addstu n/NAME h/TELEGRAM_HANDLE e/EMAIL`

* Telegram handle must start with a '@', followed by an alphanumeric character.
* After the first character, telegram handle can only contain alphanumeric character or underscore.
* Email can only contain alphanumeric characters.

Examples:

* `addstu n/John Lim Jun Jie h/@johnlimjj e/johnlim@example.com`
* `addstu n/Mary Tan Xiao Li h/@marytxl e/marytxl@example.com`

### Editing a student: `editstu`

Edits an existing student in the student list.
If a student's details are wrongly or have changed, you can modify them.

Format: `editstu INDEX [n/NAME] [h/TELEGRAM_HANDLE] [e/EMAIL] [a/ATTENDANCE]`

* Edits the student at the specified INDEX. The index represents the index number of the student in the student list.
  The index must be a positive integer 1, 2…
* At least one of the fields (E.g. [n/NAME] or [e/EMAIL]) must be provided.
* Existing fields will be updated to the input values.
* Editing a student with the same details as the student's original details will be accepted.
  (E.g. executing `editstu 1 h/@test` when student 1's telegram handle is already `@test`, will still be accepted as an edit.)
* Input attendance value without any extra '0's before and after the intended attendance value. (E.g. '0' instead of '0000' and '3' instead of '003').

Examples:

* `editstu 1 h/@johnlim e/jljj@example.com` Edits the telegram handle and email of the 1st student to @johnlim and
  jljj@example.com respectively.
* `editstu 3 n/Mary Lee Jing Yi` Edits the name of the 3rd student to Mary Lee Jing Yi.
* `editstu 2 a/5` Edits the attendance number of the 2nd student to 5.

### Adding student's attendance : `attendance`

Increases student's attendance by 1.
Over the course of the semester, after each tutorial, you can update the students' attendance.

Format: `attendance INDEX`

* Increment attendance to the student at the specified INDEX.
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1, 2, 3, ….

Example:

* `attendance 1`

### Adding student's response: `addresponse`

Edits the number of messages a specified student sent during tutorial.
This feature helps you to track how much the student has been actively participating in the tutorial through the 
number of messages sent by him.

Format: `addresponse INDEX m/MESSAGE_COUNT`

* Edits response count of the student at the specified INDEX.
    * If `addresponse 1 m/7` is keyed in after `addresponse 1 m/2`, the response count for the first
      student in the student list will be 7 instead of 2.
* The `INDEX` refers to the index number shown in the displayed student list.
* The `INDEX` must be a positive integer 1, 2, 3, ...
* The `MESSAGE_COUNT` must be a positive integer 1, 2, 3, ...
* If `m\0000000000` is given as an input, 0s will not be truncated and response will be displayed as
  `response: 000000000`

Example:

* `addresponse 1 m/7`

### Adding help tag: `helpstu`

Adds a help tag to an existing student.
This feature helps you to visually see which student specifically needs help. 

Format: `helpstu INDEX`

* Adds a help tag to the student at the specified INDEX.
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1, 2. 3, ….

Example:

* `helpstu 2`

### Deleting a student: `deletestu`

Removes a specific student.
If a student has left the module or swapped tutorial group, you can delete him from the list. 

Format: `deletestu INDEX`

* Deletes the student at the specified INDEX.
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1, 2. 3, ….

Example:

* `deletestu 2`

### Finding a student: `findstu`

Finds one or more specific students.
As the student list can potentially contain more than 40 students, this feature helps you to specifically find the 
student based on the student's name.

Format: `findstu KEYWORD [MORE_KEYWORDS]`

* Finds student(s) with specified KEYWORD [MORE_KEYWORDS] in their name(s).
* The keyword is case-insensitive.

Examples:

* `findstu bob`
* `findstu john mary`

### Listing all students: `liststu`

Lists all students in the student list.
After the above feature(`findstu`) is used, you may want to view all the students that you have again. This feature 
helps you to do so.

Format: `liststu`

Example:

* `liststu`

### Questions
Sometimes, you may receive questions that you may not have the answer to or you may not have enough time to answer 
all of them during the tutorial. SETA allows you to track these questions. Furthermore, if you find a question that 
requires urgent clarification or critical thinking, you can indicate on the user interface that these questions are 
important. For questions requiring critical thinking, it would be also good to share them with other TAs so that the 
rest of the module's students can benefit from knowing this question.

### Adding a question : `addq`

Adds a question to the question list.
This feature allows you to keep track of your students' questions.

Format: `addq QUESTION_DESCRIPTION`

Example:

* `addq what is the difference between self-invocation and call-backs for sequence diagrams?`

### Marking a question : `markq`

Marks a question as important.
This feature enables you to indicate on the user interface that this question is important and requires more 
attention compared to the other questions.

Format: `markq INDEX`

* Marks the question at the specified INDEX as important.
* The index refers to the index number shown in the displayed question list.
* The index must be a positive integer 1, 2, 3, ...
* The index must be within the number of questions in the question list. E.g. There are 4 questions. The possible
  indexes are 1, 2, 3 and 4.

Example:

* `markq 1` marks the first question in the question list as important

### Unmarking a question : `unmarkq`

Allows you to mark a question as unimportant if the question was previously or mistakenly marked as important.

Format: `unmarkq INDEX`

* Marks the question at the specified INDEX as unimportant.
* The index refers to the index number shown in the displayed question list.
* The index must be a positive integer 1, 2, 3, ...
* The index must be within the number of questions in the question list. E.g. There are 4 questions. The possible
  indexes are 1, 2, 3 and 4.

Example:

* `unmarkq 1` marks the first question in the question list as unimportant

### Deleting a question : `deleteq`

Deletes a question in the question list.
Once a question has been addressed, you can delete it from the list.

Format: `deleteq INDEX`

* The index refers to the index number shown in the displayed question list.
* The index must be a positive integer 1, 2, 3, ...
* The index must be within the number of questions in the question list. E.g. There are 4 questions. The possible
  indexes are 1, 2, 3 and 4.

Example:

* `deleteq 1` deletes the first question from the question list

### Tutorials
Each tutorial has a group number, a topic to focus on and a date and time. These correspond to the `GROUP_NUMBER`, 
`CONTENT` and `DATE TIME` parameters accordingly.


### Adding a tutorial : `addtut`

Adds a tutorial to the tutorial list.

Format: `addtut g/GROUP_NUMBER c/CONTENT t/DATE TIME`

* The format of the date must be in the YYYY-MM-DD.
* The format of the time must be in 24h format HHmm.

Example:

* `addtut g/T08 c/UML diagrams t/2022-10-01 1400`

### Deleting a tutorial : `deletetut`

Deletes a tutorial in the tutorial list.

Format: `deletetut INDEX`

* Deletes the tutorial at the specified INDEX.
* The index refers to the index number shown in the displayed tutorial list.
* The index must be a positive integer 1, 2. 3, … .
*

Example:

* `deletetut 1`deletes the first tutorial from the tutorial list

### Marking a tutorial: `marktut`

Marks the tutorial and its content as done.
When the tutorial is over, you can indicate on the user interface that it is done.

Format: `marktut INDEX`

* Marks the tutorial at the specified `INDEX`.
* The index refers to the index number shown in the displayed tutorial list.
* The index must be a positive integer 1, 2, 3, ... .

Example:

* `marktut 1` marks the first tutorial from the tutorial list as done.

### Unmarking a tutorial: `unmarktut`

Marks content in the tutorial as undone.
If a tutorial was mistakenly marked as done, you can undo it using this feature.

Format: `unmarktut INDEX`

* Marks the tutorial at the specified `INDEX`.
* The index refers to the index number shown in the displayed tutorial list.
* The index must be a positive integer 1, 2, 3, ...

Example:

* `unmarktut 1` marks the first tutorial from the tutorial list as undone.

### Clearing data in SETA : `clear`

Clears the data in SETA. This is used when you just started using SETA (to clear the sample data given) or when you 
want to start adding students, questions and tutorials from scratch again.

Format: `clear `

### Exiting the SETA : `exit`

Exits the program.

Format: `exit`

### Saving the data

SETA data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

### Editing the data file

SETA data are saved as a JSON file `[JAR file location]/data/seta.json`. Advanced users are welcome to update data
directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SETA will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous SETA home folder.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action         | Format, Examples                           |
|----------------|--------------------------------------------|
| **Add**        | `addstu`, `addq`, `addtut`                 |
| **Attendance** | `attendance`, `addresponse`                |
| **Delete**     | `deletestu`, `deleteq`, `deletetut`        |
| **Edit**       | `editstu`                                  |
| **Find**       | `findstu`                                  |
| **List**       | `liststu`                                  |
| **Mark**       | `markq`, `unmarkq`, `marktut`, `unmarktut` |
| **Tag**        | `helpstu`                                  |
| **Clear**      | `clear`                                    |
| **Exit**       | `exit`                                     |
