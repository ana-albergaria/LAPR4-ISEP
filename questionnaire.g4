grammar questionnaire ;

/********* UTILS *********/

alfanumerico : PALAVRA | DIGITO ;
frase : PALAVRA (VIRGULA? ESPACO (PALAVRA|DIGITO)*)*;
//title: it is a mandatory short sentence (for questionnaire and section)
title : frase ;
message : frase
        | (frase NEWLINE)+ ;


/********* QUESTIONNAIRE *********/

survey : questionnaire_id (ESPACO)? title NEWLINE message? (section)+ NEWLINE message ;


//id -> mandatory alphanumeric value to univocally identify a questionnaire (E.g.: "COSM22-01")
questionnaire_id : alfanumerico+ ;


/********* SECTION *********/

//---> Falta incluir Obligatoriness e Repeatability <---
section : numeric_id /*title NEWLINE (frase+ NEWLINE)* NEWLINE obligatoriness NEWLINE (question)+*/;

numeric_id : (DIGITO)+ ;

obligatoriness : MANDATORY
               | OPTIONAL
               | CONDITION_DEPENDENT ;


/********* QUESTION *********/

//---> Falta incluir Type, Obligatoriness e Extra Info <---
question: numeric_id title NEWLINE message? ;


question_text : frase PONTO_INTERROGACAO
            | frase PONTO_EXCLAMACAO;

type: 'free text'
    | 'numeric'
    | 'single-choice' single_choice
    | 'multiple_choice' multiple_choice
    | 'single-choice with input' single_choice_input
    | 'multiple-choice with input' multiple_choice_input
    | 'sorting options' sorting_option
    | 'scaling options' scaling_option;



single_choice: (question)+;
single_choice_input: (question)+;
multiple_choice: (question)+;
multiple_choice_input: (question)+;
sorting_option: (question)+;
scaling_option: (question)+;





/********* TOKENS *********/



DIGITO : [0-9] ;
PALAVRA : [a-zA-Z]+;
VIRGULA : ',' ;
ESPACO : ' ' ;
PONTO_INTERROGACAO : '?' ;
PONTO_EXCLAMACAO: '!';
MANDATORY: 'mandatory';
OPTIONAL: 'optional';
CONDITION_DEPENDENT: 'condition dependent';
NEWLINE:'\r'?'\n' ;         //return end of line
WS : [ \t\r.?!*'-]+ -> skip ; //skip spaces, tabs, newlines


