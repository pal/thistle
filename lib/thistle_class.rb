# Helper code for Thistle, take 1
module Thistle
  class Thistle
    
    def self.webrat_session=(session)
      @@webrat_session = session
    end
    
    require 'spec'
    require 'spec/expectations'
    include Spec::Matchers
    
    require 'webrat'
    include Webrat::Locators
    
    @path_to = {
      "the home page" => "/"
    }
    
    def self.visit (page) 
      #@browser.goto page # celerity
      #visits(@path_to[page]) # webrat
      @@webrat_session.visit page
    end
    
    def self.click (locator) 
      #@browser.button(:value, locator).click # celerity
      # refactor me!
=begin
      if  @@webrat_session.find_button(locator) != nil
        puts "Found button #{locator}"
      elsif  @@webrat_session.find_link(locator) != nil
        puts "Found link #{locator}"
      else
        puts "Nothing found to click"
      end
=end
      
      begin
        @@webrat_session.click_button locator
      rescue
        #else
        begin
          @@webrat_session.click_link /#{Regexp.escape(locator)}/
        rescue
          $stderr.puts $!.inspect
          raise "Cannot find #{locator}"
        end
        end
      end
    end
    
    def self.enter_text (text, locator) 
      #@browser.text_field(:name, locator).set(text) # celerity
      
      # todo add FieldPrependedByLocator to webrat
      
      puts "Looking for field #{locator}"
      
      
      if  @@webrat_session.field(locator) != nil
        puts "Found field #{locator} as field"
      elsif  Webrat::XML.attribute(field_element, "id") =~ /#{Regexp.escape(locator)}/
        puts "Found field #{locator} by regex"
      #end
      
      
      # use FieldLabeledLocator as well?
      @@webrat_session.fill_in locator, :with => text
    end
    
    def self.login (username, password) 
      # find the two fields and the submit-button
      raise "Thistle Login-method not implemented yet!"
    end
    
    def self.check_locator_value (locator, expected_value)
      # refactor pls!
      if locator == "title"
        @@webrat_session.response.title.should =~ /#{Regexp.escape(expected_value)}/m 
      else
        @@webrat_session.response.body.should =~ /#{Regexp.escape(expected_value)}/m 
      end
    end
    
    def self.match_locator_value (locator, regex_pattern)
      #  @browser.title.should =~ Regexp.new(regex_pattern) # celerity
      pending
      raise "Thistle match_locator_value-method not implemented yet!"
      end
    
    def self.check_for_text (text, should_be_present = true)
      if !should_be_present
        @@webrat_session.response.body.should_not =~ /#{Regexp.escape(text)}/m 
      else
        @@webrat_session.response.body.should =~ /#{Regexp.escape(text)}/m 
      end
    end
    
  end
end 

World(Thistle)