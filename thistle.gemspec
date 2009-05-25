# -*- encoding: utf-8 -*-

Gem::Specification.new do |s|
  s.name = %q{thistle}
  s.version = "0.0.1.3"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.authors = ["Pal Brattberg"]
  s.date = %q{2009-05-25}
  s.description = %q{Thistle is a language- and framework agnostic vocabulary for testing web sites.}
  s.email = %q{brattberg@gmail.com}
  s.files = ["README", "lib/thistle.rb", "lib/thistle_class.rb"]
  s.has_rdoc = true
  s.homepage = %q{http://github.com/pal/thistle}
  s.rdoc_options = ["--inline-source", "--charset=UTF-8"]
  #s.require_paths = ["lib"]
  #s.rubyforge_project = %q{thistle}
  s.rubygems_version = %q{1.3.0}
  s.summary = %q{Thistle is a language- and framework agnostic vocabulary for testing web sites.}
  
  s.requirements << 'cucumber, rspec and webrat'

  if s.respond_to? :specification_version then
    current_version = Gem::Specification::CURRENT_SPECIFICATION_VERSION
    s.specification_version = 2

    if Gem::Version.new(Gem::RubyGemsVersion) >= Gem::Version.new('1.2.0') then
      s.add_runtime_dependency(%q<rspec>, [">= 1.2.6"])
      s.add_runtime_dependency(%q<cucumber>, [">= 0.3.7"])
      s.add_runtime_dependency(%q<webrat>, [">= 0.4.4"])
    else
      s.add_dependency(%q<rspec>, [">= 1.2.6"])
      s.add_dependency(%q<cucumber>, [">= 0.3.7"])
      s.add_dependency(%q<webrat>, [">= 0.4.4"])
    end
  else
    s.add_dependency(%q<rspec>, [">= 1.2.6"])
    s.add_dependency(%q<cucumber>, [">= 0.3.7"])
    s.add_dependency(%q<webrat>, [">= 0.4.4"])
  end
end