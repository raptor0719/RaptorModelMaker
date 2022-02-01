# RaptorModelMaker
Tool for creating models for the RaptorEngine.

# TODO
- Animation Preview Control Panel
	- Play/Pause Combined Button
	- Stop Button
	- Step Buttons (Forward and Back)
	- Go-to-Start Button
- Sprite Library
	- Holds the information for sprite collections, sprites, and their attach points
	- Format
		- Magic Number
		- Sprite Library Name
		- Sprite Collection Count
		- Sprite Collections
			- Collection Name
			- Sprite (One for each view direction, 8 total)
				- Attach Point
- Sprite Panel
	- Library Meta Actions
		- Create new sprite library
		- Load sprite library
		- Save sprite library
	- Library Modification Actions
		- Add new sprite collection
		- Modify attach point for each sprite in the collection
		- Modify name for the sprite collection
	- Model Actions
		- Attach sprite collection to hardpoint
		- Unattach sprite collection from hardpoint
- Save/Load Model
	- Actions
		- New Model
		- Save Model
		- Load Model
	- Model Format
		- Magic Number
		- Model Name
		- Hardpoint Count
		- Hardpoints
			- Name
		- Frame Count
		- Frames
			- Name
			- Hardpoint Positions
				- x
				- y
				- z
				- rotation
		- Animation Count
		- Animations
			- Name
			- Section Count
			- Sections
				- Frame Name
				- Holds
- Export as Raptor Engine Model
	- Need to square away the engine side of things to figure this out