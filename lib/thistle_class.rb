require 'spec'
require 'spec/expectations'
require 'webrat'

# Helper code for Thistle, take 1
module Thistle
  class Thistle
    #include Spec::Matchers
    #include Webrat::Locators
    
    def initialize(session = nil) #:nodoc:
      @webrat_session = session
    end
    
    #def webrat_session=(session)
    #  @webrat_session = session
    #end
    
    #@path_to = {
    #  "the home page" => "/"
    #}
    
    def visit (page) 
      #@browser.goto page # celerity
      #visits(@path_to[page]) # webrat
      @webrat_session.visit page
    end
    
    def check_locator_value (locator, expected_value)
      # refactor pls!
      if locator == "title"
        @webrat_session.response.title.should =~ /#{Regexp.escape(expected_value)}/m 
      else
        @webrat_session.response.body.should =~ /#{Regexp.escape(expected_value)}/m 
      end
    end
    
    def enter_text (text, locator) 
      #@browser.text_field(:name, locator).set(text) # celerity
      # todo add FieldPrependedByLocator to webrat
      puts "Looking for field #{locator}"
      
=begin
      if  @webrat_session.field(locator) != nil
        puts "Found field #{locator} as field"
      elsif  Webrat::XML.attribute(field_element, "id") =~ /#{Regexp.escape(locator)}/
        puts "Found field #{locator} by regex"
      end
=end
      
      # use FieldLabeledLocator as well?
      @webrat_session.fill_in locator, :with => text
    end
    
    def login (username, password) 
      # find the two fields and the submit-button
      raise "Thistle Login-method not implemented yet!"
    end
    
    
    
    def match_locator_value (locator, regex_pattern)
      #  @browser.title.should =~ Regexp.new(regex_pattern) # celerity
      pending
      raise "Thistle match_locator_value-method not implemented yet!"
      end
    
    def check_for_text (text, should_be_present = true)
      if !should_be_present
        @webrat_session.response.body.should_not =~ /#{Regexp.escape(text)}/m 
      else
        @webrat_session.response.body.should =~ /#{Regexp.escape(text)}/m 
      end
    end
    
def click (locator) 
  #@browser.button(:value, locator).click # celerity
  # refactor me!
=begin
  if  @webrat_session.find_button(locator) != nil
    puts "Found button #{locator}"
  elsif  @webrat_session.find_link(locator) != nil
    puts "Found link #{locator}"
  else
    puts "Nothing found to click"
  end
=end

  begin
    @webrat_session.click_button locator
  rescue
    begin
      @webrat_session.click_link /#{Regexp.escape(locator)}/
    rescue
      $stderr.puts $!.inspect
      raise "Cannot find #{locator}"
    end
  end
  
  #end
end
    
  end
end 

#World(Thistle)