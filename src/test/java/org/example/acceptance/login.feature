Feature: user login

  Scenario Outline: Customer can login
    Given : The email is <email>
    And : The password is <password>
    Then customer login
    Examples:
      | email                    | password |
      | "s12029704@stu.najah.edu" | "1111"   |

  Scenario Outline: Customer enter  false Email
    Given : The email is <email>
    And : The password is <password>
    Then customer can not login
    Examples:
      | email             | password |
      | "samarium.com" | "1111"   |

  Scenario Outline: Customer enter  false Password
    Given : The email is <email>
    And : The password is <password>
    Then customer enter false pass then can not login
    Examples:
      | email                    | password   |
      | "somaaaaaaa12@gmail.com" | "12129020" |

  Scenario Outline: Admin can login
    Given : The email is <email>
    And : The password is <password>
    Then Admin can login
    Examples:
      | email                   | password |
      | "samaabusair12@gmail.com" | "120"    |

  Scenario Outline: Admin enter wrong Email
    Given : The email is <email>
    And : The password is <password>
    Then Admin cant login because email wrong
    Examples:
      | email                     | password |
      | "SamaAbosai22r@gmail.com" | "120"    |

  Scenario Outline: Admin enter wrong Password
    Given : The email is <email>
    And : The password is <password>
    Then Admin cant login because pass wrong
    Examples:
      | email                   | password |
      | "samaabusair12@gmail.com" | "121"    |

  Scenario Outline: Installer can login
    Given : The email is <email>
    And : The password is <password>
    Then Installer can login
    Examples:
      | email                 | password |
      | "lameesahmed257@gmail.com" | "121"  |

  Scenario Outline: Installer enter  false Email
    Given : The email is <email>
    And : The password is <password>
    Then Installer can't login
    Examples:
      | email              | password |
      | "samarium.com" | "121"  |

  Scenario Outline: Installer enter  false Password
    Given : The email is <email>
    And : The password is <password>
    Then Installer enter false pass then can't login
    Examples:
      | email                 | password |
      | "LamesAhmed@gmail.com" | "12129"  |