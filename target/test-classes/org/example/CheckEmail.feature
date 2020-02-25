Feature: Check Email
  I want to check my latest email

  Scenario:  Login into outlook using invalid email and get error
    Given I am on outlook.com
    When I log into outlook using email:"testgmailaccount@outlook.com" pass:"Password12345"
    Then I get an error message

  Scenario:  Login into outlook using valid creds and open email
    Given I am on outlook.com
    When I log into outlook using email:"itmanish01@hotmail.com" pass:"$FFYe/xb@8_6qEcC"
    Then load from first email from the list

  Scenario:  Login into outlook using invalid password and get error
    Given I am on outlook.com
    When I log into outlook using email:"itmanish01@hotmail.com" pass:"Password123"
    Then I get an error message

  Scenario:  Login into outlook using valid creds and open email
    Given I am on outlook.com
    When I log into outlook using email:"itmanish02@outlook.com" pass:"Y59(%Mp^w*>&5~G/"
    Then load from first email from the list