<tbody id="matCostList" v-for="mc in mcList">
<tr>
    <td>{{mc.$index+1}}</td>
    <td>{{mc.applyDate}}</td>
    <td>{{mc.locName}}</td>
    <td>{{mc.ecName}}</td>
    <td>{{mc.amount}}</td>
    <td>{{mc.ecType}}</td>
</tr>
</tbody>