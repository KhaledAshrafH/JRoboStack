*** Settings ***
Library     SeleniumLibrary
Library    Collections
Library    String
Library    Screenshot
Library    XML

Test Setup      Open Browser    ${todoUrl}    Chrome
Test Teardown   Close Browser

*** Variables ***
${todoUrl}      http://127.0.0.1:5500/todo.html
${sampleTodoTitleInput}   Task1
${sampleDescriptionInput}    Description1
${sampleTodoTitleInput2}   Task2
${sampleDescriptionInput2}    Description1
${sampleTodoTitleInput3}   Task1
${sampleDescriptionInput3}    Description3
${EMPTY}

*** Keywords ***
Sleep3
    Sleep    3

Sleep5
    Sleep    5


*** Test Cases ***

#  verify that a user can add a new TODOs, and after submit, it is added in the table and all text inputs are empty
Scenario 1 (Add new TODO)
    Maximize Browser Window
    Wait Until Page Contains Element    xPath=/html/body/div/table
    Input Text    id=todo    ${sampleTodoTitleInput}
    Input Text    id=desc    ${sampleDescriptionInput}
    Sleep3
    Click Button    xPath=//*[@id="todo-form"]/button
    Sleep3

    Element Text Should Be     id=todo      ${EMPTY}
    Element Text Should Be     id=desc      ${EMPTY}

    ${checkAdded}   Set Variable    ${False}
    ${all_table_rows}=    Get WebElements    xpath=//*[@id="todo-table"]//tr
    FOR    ${row}    IN    @{all_table_rows}
         ${rowValues}      Get Text     ${row}
         ${rowValuesStrs}         Split String    ${rowValues}
         IF    '${rowValuesStrs[1]}' == '${sampleTodoTitleInput}'
            IF     '${rowValuesStrs[2]}' == '${sampleDescriptionInput}'
                 ${checkAdded}      Set Variable    ${True}    #${}
                 BREAK
            END
         END
    END
    Should Be True    ${checkAdded}
    Sleep3



# verify user can delete a TODOs, and it is removed from the table
Scenario 2 (Delete)
    Maximize Browser Window
    Wait Until Page Contains Element    xPath=/html/body/div/table
    ${all_table_rows}=    Get WebElements    xpath=//*[@id="todo-table"]//tr
    # We Test to remove first element in table
    ${rowDeletedValues}      Get Text     ${all_table_rows[0]}
    ${rowDeletedValuesStrs}         Split String    ${rowDeletedValues}
    Click Button    xPath=/html/body/div/table/tbody/tr[1]/td[5]/button
    Sleep3

    ${checkFound}   Set Variable    ${True}
    ${all_table_rows}=    Get WebElements    xpath=//*[@id="todo-table"]//tr
    FOR    ${row}    IN    @{all_table_rows}
             ${rowValues}      Get Text     ${row}
             ${rowValuesStrs}         Split String    ${rowValues}
             IF    ${rowValuesStrs[0]} == ${rowDeletedValuesStrs[0]}
                   IF    ${rowValuesStrs[1]} == ${rowDeletedValuesStrs[1]}
                        IF    ${rowValuesStrs[2]} == ${rowDeletedValuesStrs[2]}
                             ${checkFound}      Set Variable    ${False}    #${}
                             BREAK
                        END
                   END
             END
    END
    Should Be True    ${checkFound}
    Sleep3



# verify user can update the completion of TODOs, by click the checkbox, and the table display it in the wright way
Scenario 3 (Update completion)
    Maximize Browser Window
    Wait Until Page Contains Element    xPath=/html/body/div/table
    # Test On first Element

    # Item not selected Testcase.
    # Firstly, The checkbox not selected
    Checkbox Should Not Be Selected    xPath=/html/body/div/table/tbody/tr[1]/td[4]/input
    Click Element    xPath=/html/body/div/table/tbody/tr[1]/td[4]/input
    # Fianlly, The checkbox selected after click on it
    Checkbox Should Be Selected    xPath=/html/body/div/table/tbody/tr[1]/td[4]/input
    Sleep3

    # Item selected Testcase.
    # Firstly, The checkbox selected
    Checkbox Should Be Selected    xPath=/html/body/div/table/tbody/tr[1]/td[4]/input
    Click Element    xPath=/html/body/div/table/tbody/tr[1]/td[4]/input
    # Fianlly, The checkbox not selected after click on it
    Checkbox Should Not Be Selected    xPath=/html/body/div/table/tbody/tr[1]/td[4]/input
    Sleep3



# verify that all TODOs is displayed correctly
Scenario 4 (Get all TODOs)
    Maximize Browser Window
    Wait Until Page Contains Element    xPath=/html/body/div/table
    Click Button    xPath=/html/body/div/div/div[2]/button[1]
    @{all_table_rows}=    Get WebElements    xpath=//*[@id="todo-table"]//tr
    @{allIDs}=        Create List
    Sleep5
    # Check List of items is not Empty
    Should Not Be Empty    ${all_table_rows}
    FOR    ${row}    IN    @{all_table_rows}
             ${rowValues}      Get Text     ${row}
             ${rowValuesStrs}         Split String    ${rowValues}
             ${rowValuesList}       Convert To List    ${rowValuesStrs}
             ${rowLength}       Get Length    ${rowValuesList}
             # Check that 4 items exist without check box
             Should Be Equal As Integers    ${rowLength}    ${4}

             # Check That title and decription not empties
             Should Not Be Empty    ${rowValuesList[1]}
             Should Not Be Empty    ${rowValuesList[2]}

             # Add Id to list to check uniqueness of IDs
             Append To List    ${allIDs}      ${rowValuesList[0]}
             Sleep3
    END
    # check uniqueness of IDs
    ${allIDsCopy}       Copy List    ${allIDs}
    Remove Duplicates    ${allIDs}
    Lists Should Be Equal    ${allIDs}     ${allIDsCopy}
    Sleep3



# verify that the table display only completed TODOs
Scenario 5 (Get completed TODOs)
    Maximize Browser Window
    Wait Until Page Contains Element    xPath=/html/body/div/table
    Sleep3
    Click Button    xPath=/html/body/div/div/div[2]/button[2]
    Wait Until Page Contains Element    xPath=/html/body/div/table
    Sleep3
    @{all_table_rows_selected}=    Get WebElements    xpath=//*[@id="todo-table"]//tr/td[4]/input
    FOR    ${checkbox}    IN    @{all_table_rows_selected}
             Checkbox Should Be Selected    ${checkbox}
    END
    Sleep3