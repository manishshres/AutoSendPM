Feature: Post Video On Reddit
  I want to posts youtube  videos link on the reddit

  Scenario:  User wants to submits youtube video link on reddit
    Given I am on google.com
    When I search for "Reddit login" and login using user:"throwsAwayAcccount" pass:"$2DCp))(a+&V$fQx"
    Then post video with title:"This is a test link" and video WatchID:"CNgOqNNWLQ4"