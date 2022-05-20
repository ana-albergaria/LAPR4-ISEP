grammar questionnaire ;

/********* UTILS *********/

alfanumerico : PALAVRA | DIGITO ;
frase : PALAVRA (VIRGULA? ESPACO (PALAVRA|DIGITO)*)*;
//title: it is a mandatory short sentence (for questionnaire and section)
title : frase ;
message : frase
        | (frase NEWLINE)+ ;


/********* QUESTIONNAIRE *********/

survey : questionnaire_id ESPACO title NEWLINE message? (NEWLINE section)+ NEWLINE NEWLINE message ;


//id -> mandatory alphanumeric value to univocally identify a questionnaire (E.g.: "COSM22-01")
questionnaire_id : alfanumerico+ ;


/********* SECTION *********/

//---> Falta incluir Repeatability <---
section : numeric_id title NEWLINE message? obligatoriness NEWLINE (question)+;

numeric_id : (DIGITO)+ ;

obligatoriness : 'Obligatoriness: ' (MANDATORY
               | OPTIONAL
               | CONDITION_DEPENDENT) ;


/********* QUESTION *********/

//---> Falta incluir Type, Obligatoriness e Extra Info <---
question: numeric_id question_text NEWLINE type NEWLINE message? ;

option: numeric_id PARENTISIS frase NEWLINE;

question_text : frase PONTO_INTERROGACAO ;

type: 'free text'
    | 'numeric'
    | 'single-choice' NEWLINE single_choice
    | 'multiple_choice' NEWLINE multiple_choice
    | 'single-choice with input' NEWLINE single_choice_input
    | 'multiple-choice with input' NEWLINE multiple_choice_input
    | 'sorting options' NEWLINE sorting_option
    | 'scaling options' NEWLINE scaling_option;



single_choice: (option)+;
single_choice_input: (option)+;
multiple_choice: (option)+;
multiple_choice_input: (option)+;
sorting_option: (option)+;
scaling_option: (option)+;





/********* TOKENS *********/

MANDATORY: 'mandatory';
OPTIONAL: 'optional';
CONDITION_DEPENDENT: 'condition dependent';
DIGITO : [0-9] ;
PALAVRA : [a-zA-Z]+;
PARENTISIS: ')';
VIRGULA : ',' ;
ESPACO : ' ' ;
PONTO_INTERROGACAO : '?' ;
PONTO_EXCLAMACAO: '!';
NEWLINE:'\r'?'\n' ;         //return end of line
WS : [ \t\r.?!*'-]+ -> skip ; //skip spaces, tabs, newlines


