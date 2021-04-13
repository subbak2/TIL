// https://leetcode.com/problems/longest-consecutive-sequence

class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        std::set<int> set;
        int max = 0;
        for (int n: nums)
            set.insert(n);
        
        for (std::set<int>::iterator it = set.begin(); it!=set.end(); it++)
        {
            int val = *it;
            int val_end = val;
            int count;
            while(set.find(++val_end) != set.end()){
                set.erase(val_end);
            }
            while(set.find(--val) != set.end()){
                set.erase(val);
            }
            count = val_end - val - 1;
            max = max < count ? count : max;
        }
        return max;
    }
};