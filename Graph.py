import plotly.graph_objects as go

y = [[0.37142857142857144, 0.2714285714285714, 0.21904761904761905, 0.18571428571428572, 0.16, 0.15238095238095237, 0.14693877551020404, 0.13214285714285715, 0.12380952380952383, 0.11714285714285716, 0.11688311688311688, 0.11428571428571428, 0.11428571428571431, 0.11224489795918369, 0.1161904761904762, 0.1125, 0.10756302521008401, 0.10158730158730156, 0.09774436090225565, 0.09714285714285711, 0.09251700680272106, 0.09220779220779218, 0.08944099378881992, 0.0857142857142857, 0.08342857142857145, 0.08351648351648353, 0.0814814814814815, 0.07959183673469386, 0.07980295566502461, 0.08, 0.07926267281105989, 0.07678571428571429, 0.07619047619047617, 0.0756302521008403, 0.07510204081632652, 0.073015873015873, 0.07258687258687259, 0.07218045112781954, 0.07106227106227105, 0.07142857142857142, 0.06968641114982581, 0.06938775510204082, 0.06843853820598006, 0.06688311688311686, 0.0653968253968254, 0.06459627329192548, 0.06382978723404256, 0.0630952380952381, 0.06297376093294461, 0.06400000000000002],
     [0.043857180230085425, 0.0568629896088, 0.0668629896088, 0.07454493030960863, 0.07667131545486171, 0.08422957031769877, 0.10267755656716164, 0.10391979259200636, 0.10766128919064581, 0.11296227751050206, 0.11822345057315171, 0.13579221721024137, 0.1830711287748672, 0.18839499745277316, 0.20245625931551164, 0.20709447823944485, 0.20833671426428957, 0.20833671426428957, 0.20957895028913429, 0.213746151760918, 0.213746151760918, 0.2206344416680758, 0.22349158452521867, 0.22349158452521867, 0.22587253690617107, 0.23037279854928983, 0.23147169964819092, 0.23233750051399177, 0.23776444072354067, 0.24662572436743305, 0.24990877671880837, 0.24990877671880837, 0.25203516186406144, 0.253673146520914, 0.2568096326762681, 0.2568096326762681, 0.2587092604519066, 0.29204259378523995, 0.29314149488414104, 0.2988104291245039, 0.2988104291245039, 0.30467123498530974, 0.3055370358511106, 0.3055370358511106, 0.3055370358511106, 0.30640283671691143, 0.30799013830421307, 0.3092323743290578, 0.3106372587528101, 0.3216525327869521],
     [0.09299017885484048, 0.17368629782915496, 0.2112999045772155, 0.22747969710375726, 0.23166486045936635, 0.23525363344369374, 0.24263974847698866, 0.24264307489098788, 0.23664963033051908, 0.22766465653098245, 0.22967354626115477, 0.22801829550690197, 0.22799331851325746, 0.22675425873825256, 0.2205572409094024, 0.21354319713096812, 0.2110126618910272, 0.20984284572712036, 0.21101396313282533, 0.20781419239807408, 0.20310197550259448, 0.20148203244679336, 0.1984273929534092, 0.19837621145290021, 0.20093905217601943, 0.19920618254141506, 0.20462305257894237, 0.2013527123588101, 0.19713799109397465, 0.19246129301336395, 0.18800991284976798, 0.1844397527931547, 0.18038367411572334, 0.17775078731379285, 0.1750384301527916, 0.17355795409507557, 0.1700767976596437, 0.16846378715373464, 0.16637189446175762, 0.16541903058776533, 0.16480460071403294, 0.1618136871262106, 0.16051242322768275, 0.15771309569417455, 0.15591935009233276, 0.15602320374182382, 0.15538466532957387, 0.1528619860283222, 0.1519344456598236, 0.1503345015624751]]
x = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50]
fig = go.Figure()

fig.add_trace(go.Scatter(
    x=x,
    y=y[0],
    mode='lines+markers',
    name="Precision"
))


fig.add_trace(go.Scatter(
    x=x,
    y=y[1],
    mode='lines+markers',
    name="Recall"
))

# fig.add_trace(go.Scatter(
#     x=x,
#     y=y[2],
#     mode='lines+markers',
#     name="F1"
# ))

fig.update_layout(
    title="Jaccard PRF",
    xaxis_title="K",
    yaxis_title="PRF value",
    font=dict(
        family="Courier New, monospace",
        size=18,
        color="#7f7f7f"
    )
)

fig.show()