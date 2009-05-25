#
# when time is ripe, drag this out to it's own ruby-gem
#

# the regex's should be part of the Thistle helper?

# Thistle API for cucumber

# NAVIGATION
When /^(?:I )?(?:visit|navigate to|go to|browse) (.+)$/ do |page|
  Thistle::Thistle.visit(page)
end

# CLICKING
When /^(?:I )?(?:click|press|choose) (?:the )?(?:\")?(.+?)(?:\")?(?: button)?$/ do |locator|
 Thistle::Thistle.click(locator)
end

# ENTERING DATA
When /^(?:I )?(?:enter|type|insert) (?:\")?(.+)(?:\")? (?:in the field|in|into|in the) (?:\")?([^\"]*?)(?:\")?(?: field)?$/ do |text, locator|
  Thistle::Thistle.enter_text(text, locator)
end

Given /^(?:I )?(?:enter|type|insert)(?: the following):$/ do |input_table|
  input_table.raw.each do |row|
    Thistle::Thistle.enter_text(row[1], row[0])
  end
end

# CONVIENIENCE MATCHER (version 2.0 stuff!)
Given /^(?:I )?login as "([^\"]*)" \/ "([^\"]*)"$/ do |username, password|
  Thistle::Thistle.login(username, password)
end

# check for matching title / refactor for any element
Then /^(?:the )?(?:\")?([^\"]*)(?:\")? should (?:be|equal) (?:\")?([^\"]*)(?:\")?$/ do |locator, expected_value|
  Thistle::Thistle.check_locator_value(locator, expected_value)
end

Then /^(?:the )?([^\"]*) should (?:match) ([^\"]*)$/ do |locator, regex_pattern|
  Thistle::Thistle.match_locator_value(locator, regex_pattern)
end

Then /^(?:I )?should see (?:the text )?(?:\")?([^\"]*)(?:\")?$/ do |expected_text|
  Thistle::Thistle.check_for_text(expected_text)
end

Then /^(?:I )?should not see (?:the text )?(?:\")?([^\"]*)(?:\")?$/ do |expected_text|
  Thistle::Thistle.check_for_text(expected_text, false)
end


# below here is inspiration! =)

=begin

Then /^the page should load in less than (\d+) seconds$/ do |timeout|
  # no need for this in Watir, all checks wait by default
  # see http://wiki.openqa.org/display/WTR/How+to+wait+with+Watir
end
=end





=begin 
case PLATFORM
when /darwin/
  require 'safariwatir'
  Browser = Watir::Safari
when /win32|mingw/
  require 'watir'
  Browser = Watir::IE
  # Browser.speed = :fast
when /java/
  require 'celerity'
  Browser = Celerity::Browser
else
  raise "This platform is not supported (#{PLATFORM})"
end
=end

=begin
# default from cukes
# http://github.com/aslakhellesoy/cucumber/raw/9ecb25dcf645d0f55cb27c2383ae3d9763399f93/generators/cucumber/templates/common_webrat.rb
When /I press "(.*)"/ do |button|
  clicks_button(button)
end

When /I follow "(.*)"/ do |link|
  clicks_link(link)
end

When /I enter "(.*)" for "(.*)"/ do |value, field|
  fills_in(field, :with => value) 
end

When /I check "(.*)"/ do |field|
  checks(field) 
end



Then /I should see "(.*)"/ do |text|
  response.body.should =~ /#{text}/m
end

Then /I should not see "(.*)"/ do |text|
  response.body.should_not =~ /#{text}/m
end





#require 'celerity'
require 'spec'
require 'hpricot'

#Before do
# @browser = Browser.new #Celerity::IE.new #Browser.new
#  @browser.speed = :fast
#end

After do
  @browser.close
end

Given /^I navigate to (.+)$/ do |url|
  @browser.goto url
end

Then /^the title should be "(.+)"$/ do |text|
  @browser.title.should == text
end

Then /^there should be a link to (.+)(?: present)?$/ do |url|
  @browser.link(:url, url)
end

When /^I enter "(.+)" in the (.+) field$/ do |text, locator|
  @browser.text_field(:name, locator).set(text)
end

When /^press the "(.+)" button$/ do |locator|
  @browser.button(:value, locator).click
end

Then /^the page should load in less than (\d+) seconds$/ do |timeout|
  # no need for this in Watir, all checks wait by default
  # see http://wiki.openqa.org/display/WTR/How+to+wait+with+Watir
end
=end
