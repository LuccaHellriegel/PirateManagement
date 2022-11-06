# Story Writing: 
- User Story
- Acceptance Criteria
- done by Product Owner
# Customer Refinement
- done with Domain Experts
# Team Refinement
- Add tech details section to story
- corrections
# Estimation
- estimate complexity
# Task Breakdown (return as needed)
- split up the work in tasks
- make sure to add task for all the big technical challenges
- if necessary add a task for a general solution strategy, if more design is appropriate
# Domain Model (return as needed)
- Create at least a rough sketch of the model (e.g. drawing.net / Excalidraw / maaaaybe UML)
- Example: https://docs.arc42.org/tips/8-7/
- Create / Update Bounded Context 
- Create / Update Entities
# UX Prototype (return as needed)
- done by UX person or rough sketch by team (e.g. Excalidraw)
# Story Kickoff
- Create Feature Branch
- Create API Contract Branch
- Design general API Contract
- Add general Stubs (can be in API Contract branch if done quickly)
# Incremental Development (loop until done)
- Decide on which task(s) to work in this increment
- Create Increment Branch
- API Contract Improvement
- Create additional Stubs
- Implement Controllers (API Interfaces) / Application Ports
- Implement Application Services
- Implement Domain Services
- Implement Model
- Implement Repositories / Domain Ports
- Implement automated testing (can be done before, during or after other implementations)
- Manual testing (tip: use the VS Code extension Thunder Client and load your API Contract there in Collections -> import)
- Create Increment PR
- Await Code Review (can start next increment on separate branch while waiting)
# Infra
- if necessary, can be mixed into Tasks or done separately
# Feature PR
- full PR
# Dev Deployment
- automatic via merged PR
# Domain Review
- review of finished feature
# Customer Approval
- during Sprint Demo
- done by Customer