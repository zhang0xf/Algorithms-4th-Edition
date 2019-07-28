#include <vector>
#include <iostream>

using namespace std;

int main(int argc, char *argv[])
{
	vector<int> nums;
	nums.assign(3, 0);

	int i = 0;

	while (cin >> nums[i])
	{
		i++;
		if(i >= (int)nums.size())
			break;
	}

	if(nums[0] == nums[1] && nums[1] == nums[2])
		cout << "equal" << endl;
	else
		cout << "not equal" << endl;

	system("pause");
	return 0;
}
