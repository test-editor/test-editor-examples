require browserPath

# MyFirstTestCase implements MySpecification

config MyFirstConfig

* Open application and navigate to the question of life
  
  Component: Browser
  - Start browser @browserPath
  - Navigate to "http://org.question-of-life/index.html"

* Enter the question

  Macro: MyMacroLib
  - Ask "What's the meaning of life?"
  
//  Component: QOLPage
//  - Enter "What's the meaning of life?" into <QuestionField>
//  - Click <AskButton>
  
* Validate the correct answer to be _42_

  Component: QOLPage
  - answer = Read <AnswerField>
  - assert answer = "42"
